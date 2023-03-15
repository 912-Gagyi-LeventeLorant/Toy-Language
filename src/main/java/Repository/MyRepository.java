package Repository;

import Exceptions.MyException;
import Model.PrgState;
import Model.Types.StringType;
import Model.Values.StringValue;

import java.io.*;
import java.util.List;

public class MyRepository implements MyIRepository {

    List<PrgState> crtPrg;



    PrintWriter logFile;

    public String[] getIds(){
        String[] ids = new String[crtPrg.size()];
        for (int i = 0; i<crtPrg.size(); i++) ids[i] = String.valueOf((Integer.parseInt(crtPrg.get(i).getId())));
        return ids;
    }
    public MyRepository(List<PrgState> prg, String file) throws IOException {

        crtPrg = prg;

        new FileWriter(file,false).close();

        logFile= new PrintWriter(new BufferedWriter(new FileWriter(file, true)));


    }

    public PrgState getPrg(int i)
    {
        return crtPrg.get(i);
    }

    @Override
    public List<PrgState> getPrgList() {
        return crtPrg;
    }

    @Override
    public void setPrgList(List<PrgState> newList) {
        crtPrg = newList;
    }


    @Override
    public void logPrgStateExec(PrgState prgState) throws MyException {
        logFile.println(prgState.toString());
        logFile.flush();
        logFile.println();
        logFile.flush();
    }
}
