package Controller;

import Exceptions.MyException;
import Model.PrgState;
import Model.Statements.IStmt;
import Model.Structures.MyDictionary;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIStack;
import Model.Values.Value;
import Repository.MyIRepository;
import Repository.MyRepository;
import Model.GarbageCollector;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class MyController implements MyIController {

    public MyIRepository repo ;

    ExecutorService executor;

    public MyController(MyIRepository nr) throws IOException {
        repo = nr;

    }

    @Override
    public void allStep() throws MyException, IOException, InterruptedException {
        executor = Executors.newFixedThreadPool(2);
        //remove the completed programs
        List<PrgState> prgList=removeCompletedPrg(repo.getPrgList());
        while(prgList.size() > 0){
            List<Collection<Value>> allSymTable = prgList.stream().map((PrgState p) -> (p.getSymTable().getContent().values())).collect(Collectors.toList());
            Collection<Value> merged = Stream.of(allSymTable).flatMap(Collection::stream).flatMap(Collection::stream).collect(Collectors.toList());
            prgList.get(0).getHeap().setContent(GarbageCollector.unsafeGarbageCollector(GarbageCollector.getAddrFromSymTable(merged), prgList.get(0).getHeap().getContent()));
            oneStepForAllPrg(prgList);
            //remove the completed programs
            prgList=removeCompletedPrg(repo.getPrgList());
        }
        executor.shutdownNow();
        //HERE the repository still contains at least one Completed Prg
        // and its List<PrgState> is not empty. Note that oneStepForAllPrg calls the method
        //setPrgList of repository in order to change the repository

        // update the repository state
        repo.setPrgList(prgList);
    }

    public void oneStepForAllPrg(List<PrgState> prgList) throws InterruptedException, MyException, IOException {

        prgList=removeCompletedPrg(repo.getPrgList());

        executor = Executors.newFixedThreadPool(2);
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });

        List<Callable<PrgState>> callList = prgList.stream()
                .map((PrgState p) -> (Callable<PrgState>)(() -> {return p.oneStep();}))
                .collect(Collectors.toList());

        List<PrgState> newPrgList =
                executor.invokeAll(callList).stream().map(future -> {
                            try {
                                return future.get();
                            } catch (InterruptedException | ExecutionException e) {
                                throw new RuntimeException(e);
                            }
                        }).filter(p -> p!=null)
                                    .collect(Collectors.toList());
        prgList.addAll(newPrgList);

        //after the execution, print the PrgState List into the log file
        prgList.forEach(prg -> {
            try {
                repo.logPrgStateExec(prg);
                System.out.println(prg);
            } catch (MyException e) {
                throw new RuntimeException(e);
            }
        });
        //Save the current programs in the repository


        repo.setPrgList(prgList);

        prgList=removeCompletedPrg(repo.getPrgList());


        repo.setPrgList(prgList);


    }


    List<PrgState> removeCompletedPrg(List<PrgState> inPrgList)
    {
        return inPrgList.stream()
                .filter(p -> p.isNotCompleted())
                .collect(Collectors.toList());
    }

    public List<PrgState> getAlivePrg()
    {
        return removeCompletedPrg(repo.getPrgList());
    }

    public String[] getPrgIds(){
        return repo.getIds();
    }

}
