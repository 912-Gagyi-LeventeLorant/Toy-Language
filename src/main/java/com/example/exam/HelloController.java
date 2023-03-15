package com.example.exam;


import Controller.MyController;
import Exceptions.MyException;
import Model.Expression.*;
import Model.PrgState;
import Model.Statements.*;
import Model.Structures.MyDictionary;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;
import Repository.MyIRepository;
import Repository.MyRepository;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.IOException;
import java.util.*;

public class HelloController {


    @FXML
    ListView<String> codeTable = new ListView<>();

    private boolean once = true;
    @FXML
    private ListView<String> exeStack = new ListView<>();

    public MyController[] ctr = new MyController[1000];
    public PrgState[] prg = new PrgState[1000];

    public int chosenNr,chosenCrtNr;
    @FXML
    private TableView<TableItem> heapView = new TableView<>();


    @FXML
    private TableView<TableItem> latchTableView = new TableView<>();

    @FXML
    private ListView<String> fileTableView;

    @FXML
    private ListView<String> outView;


    @FXML
    private ListView<String> prgIdView;


    @FXML
    private TableView<TableItem> symTableView = new TableView<>();

    public Stage mainStage;


    @FXML
    private TextField prgCounter;


    public void initialize() throws IOException, MyException {



        TableColumn address = new TableColumn("Address");
        address.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn id = new TableColumn("ID");
        id.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn value = new TableColumn("Value");
        value.setCellValueFactory(new PropertyValueFactory<>("value"));

        TableColumn value1 = new TableColumn("Value");
        value1.setCellValueFactory(new PropertyValueFactory<>("value"));



        TableColumn latch = new TableColumn("LatchNr");
        latch.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn value2 = new TableColumn("Value");
        value2.setCellValueFactory(new PropertyValueFactory<>("value"));;


        heapView.getColumns().addAll(address, value);

        symTableView.getColumns().addAll(id, value1);

        latchTableView.getColumns().addAll(latch,value2);

        int last = 1;




        if(once) {

            try {

                IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new IfStmt(new RelationExp(new ValueExp(new IntValue(2)), new ValueExp(new IntValue(2)), "<"), new AssignStmt("v", new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                new CompStmt(new PrintStmt(new VarExp("v")),
                                        new EndStmt())));

                ex1.typecheck(new MyDictionary<>());

                prg[last] = new PrgState(ex1);
                MyIRepository repo1 = new MyRepository(List.of(prg[last]), "log"+ last +".txt");
                ctr[last] = new MyController(repo1);
                codeTable.getItems().add(prg[last].getCode());
                last ++;
            } catch (MyException | IOException e) {
                System.out.println(e.toString());
            }
            ;
            try {

                IStmt ex2 = new CompStmt(new VarDeclStmt("a", new IntType()),
                        new CompStmt(new VarDeclStmt("b", new IntType()),
                                new CompStmt(new AssignStmt("a", new ArithExp('+', new ValueExp(new IntValue(2)),
                                        new ArithExp('*', new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
                                        new CompStmt(new AssignStmt("b", new ArithExp('+', new VarExp("a"),
                                                new ValueExp(new IntValue(1)))),
                                                new CompStmt(new PrintStmt(new VarExp("b")),
                                                        new EndStmt())))));

                ex2.typecheck(new MyDictionary<>());

                prg[last] = new PrgState(ex2);
                MyIRepository repo2 = new MyRepository(List.of(prg[last]), "log"+ last +".txt");
                ctr[last] = new MyController(repo2);
                codeTable.getItems().add(prg[last].getCode());
                last ++;
            } catch (MyException | IOException e) {
                System.out.println(e.toString());
            }
            ;
            try {

                IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
                        new CompStmt(new VarDeclStmt("v", new IntType()),
                                new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                                        new CompStmt(new IfStmt(new VarExp("a"), new AssignStmt("v", new ValueExp(new
                                                IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))),
                                                new CompStmt(new PrintStmt(new VarExp("v")), new EndStmt())))));


//
//        IStmt ex3 = new CompStmt(new VarDeclStmt("a", new BoolType()),
//                new CompStmt(new VarDeclStmt("v", new IntType()),
//                        new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(false))),
//                                new CompStmt(new IfStmt(new VarExp("a"), new VarDeclStmt("w",new IntType()),
//                                        new AssignStmt("v", new ValueExp(new IntValue(3)))),
//                                        new CompStmt( new PrintStmt(new
//                                                VarExp("v")), new PrintStmt(new VarExp("w")))))));


                ex3.typecheck(new MyDictionary<>());

                prg[last] = new PrgState(ex3);
                MyIRepository repo3 = new MyRepository(List.of(prg[last]), "log"+ last +".txt");
                ctr[last] = new MyController(repo3);
                codeTable.getItems().add(prg[last].getCode());
                last ++;
            } catch (MyException | IOException e) {
                System.out.println(e.toString());
            }
            ;
            try {

                IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()),
                        new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("repo.txt"))),
                                new CompStmt(new OpenRFileStmt(new VarExp("varf")),
                                        new CompStmt(new VarDeclStmt("varc", new IntType()),
                                                new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                        new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                                                        new CompStmt(new PrintStmt(new VarExp("varc")),
                                                                                new CompStmt(new CloseRFile(new VarExp("varf")), new EndStmt())))))))));

                ex4.typecheck(new MyDictionary<>());

                prg[last] = new PrgState(ex4);
                MyIRepository repo4 = new MyRepository(List.of(prg[last]), "log"+ last +".txt");
                ctr[last] = new MyController(repo4);
                codeTable.getItems().add(prg[last].getCode());
                last ++;
            } catch (MyException | IOException e) {
                System.out.println(e.toString());
            }
            ;
            try {

                IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStmt(new AllocateHeapStmt("v", new ValueExp(new IntValue(20))),
                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                        new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(15))),
                                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))), new CompStmt(new AllocateHeapStmt("v", new ValueExp(new IntValue(88))), new EndStmt()))))));

                ex5.typecheck(new MyDictionary<>());

                prg[last] = new PrgState(ex5);
                MyIRepository repo5 = new MyRepository(List.of(prg[last]), "log"+ last +".txt");
                ctr[last] = new MyController(repo5);
                codeTable.getItems().add(prg[last].getCode());
                last ++;
            } catch (MyException | IOException e) {
                System.out.println(e.toString());
            }

            try {

                IStmt ex6 = new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))),
                                new CompStmt(new WhileStmt(new RelationExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">="),
                                        new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp('-', new VarExp("v"), new ValueExp(new IntValue(2)))))), new CompStmt(new PrintStmt(new VarExp("v")), new EndStmt()))));

                ex6.typecheck(new MyDictionary<>());

                prg[last] = new PrgState(ex6);
                MyIRepository repo6 = new MyRepository(List.of(prg[last]), "log"+ last +".txt");
                ctr[last] = new MyController(repo6);
                codeTable.getItems().add(prg[last].getCode());
                last ++;
            } catch (MyException | IOException e) {
                System.out.println(e.toString());
            }

            try {

                IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
                        new CompStmt(new AllocateHeapStmt("v", new ValueExp(new IntValue(20))),
                                new CompStmt(new WriteHeapStmt("v", new ValueExp(new IntValue(30))), new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v"))),
                                        new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))),
                                                new CompStmt(new AllocateHeapStmt("a", new VarExp("v")),
                                                        new CompStmt(new AllocateHeapStmt("v", new ValueExp(new IntValue(40))),
                                                                new CompStmt(new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))),
                                                                        new CompStmt(new AllocateHeapStmt("a", new VarExp("v")),
                                                                                new CompStmt(new PrintStmt(new ReadHeapExp(new ReadHeapExp(new VarExp("a")))),
                                                                                        new EndStmt()))))))))));

                ex7.typecheck(new MyDictionary<>());

                prg[last] = new PrgState(ex7);
                MyIRepository repo7 = new MyRepository(List.of(prg[last]), "log"+ last +".txt");
                ctr[last] = new MyController(repo7);
                codeTable.getItems().add(prg[last].getCode());
                last ++;
            } catch (MyException | IOException e) {
                System.out.println(e.toString());
            }

            try {

                IStmt ex8 = new CompStmt(new VarDeclStmt("v", new IntType()),
                        new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                                new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))),
                                        new CompStmt(new AllocateHeapStmt("a", new ValueExp(new IntValue(22))),
                                                new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(30))),
                                                        new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))),
                                                                new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new ReadHeapExp(new VarExp("a"))))))),
                                                        new CompStmt(new PrintStmt(new VarExp("v")),
                                                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("a"))), new EndStmt())))))));

                ex8.typecheck(new MyDictionary<>());

                prg[last] = new PrgState(ex8);
                MyIRepository repo8 = new MyRepository(List.of(prg[last]), "log"+ last +".txt");
                ctr[last] = new MyController(repo8);
                codeTable.getItems().add(prg[last].getCode());
                last ++;
            } catch (MyException | IOException e) {
                System.out.println(e.toString());
            }

            try {

                IStmt ex9 = new CompStmt(new VarDeclStmt("v", new BoolType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new CompStmt(new PrintStmt(new VarExp("v")), new EndStmt())));

                ex9.typecheck(new MyDictionary<>());

                prg[last] = new PrgState(ex9);
                MyIRepository repo9 = new MyRepository(List.of(prg[last]), "log"+ last +".txt");
                ctr[last] = new MyController(repo9);
                codeTable.getItems().add(prg[last].getCode());
                last ++;
            } catch (MyException | IOException e) {
                System.out.println(e.toString());
            }

            try {
                IStmt ex10 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("b", new RefType(new IntType())),
                                new CompStmt(new VarDeclStmt("v", new IntType()),
                                        new CompStmt(new AllocateHeapStmt("a", new ValueExp(new IntValue(0))),
                                                new CompStmt(new AllocateHeapStmt("b", new ValueExp(new IntValue(0))),
                                                        new CompStmt(new WriteHeapStmt("a", new ValueExp(new IntValue(1))),
                                                                new CompStmt(new WriteHeapStmt("b", new ValueExp(new IntValue(2))),
                                                                        new CompStmt(new CondAssignStmt("v", new RelationExp(new ReadHeapExp(new VarExp("a")), new ReadHeapExp(new VarExp("b")), "<"),new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))),
                                                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                                                        new CompStmt(new CondAssignStmt("v", new RelationExp(new ArithExp('-', new ReadHeapExp(new VarExp("b")), new ValueExp(new IntValue(2))), new ReadHeapExp(new VarExp("a")),">"), new ValueExp(new IntValue(100)),new ValueExp(new IntValue(200))),
                                                                                                new CompStmt(new PrintStmt(new VarExp("v")),
                                                                                                        new EndStmt())))))))))));

                ex10.typecheck(new MyDictionary<>());

                prg[last] = new PrgState(ex10);
                MyIRepository repo10 = new MyRepository(List.of(prg[last]), "log"+ last +".txt");
                ctr[last] = new MyController(repo10);
                codeTable.getItems().add(prg[last].getCode());
                last ++;


            }
            catch (MyException | IOException e) {
                System.out.println(e.toString());
            }


            try {
                IStmt ex11 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())),
                        new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())),
                                new CompStmt(new VarDeclStmt("v3", new RefType(new IntType())),
                                        new CompStmt(new VarDeclStmt("cnt", new IntType()),
                                                new CompStmt(new AllocateHeapStmt("v1", new ValueExp(new IntValue(2))),
                                                        new CompStmt(new AllocateHeapStmt("v2", new ValueExp(new IntValue(3))),
                                                                new CompStmt (new AllocateHeapStmt("v3", new ValueExp(new IntValue(4))),
                                                                        new CompStmt(new NewLatchStmt("cnt", new ReadHeapExp(new VarExp("v2"))),
                        new CompStmt(new ForkStmt(new CompStmt(new WriteHeapStmt("v1", new ArithExp('*', new ReadHeapExp(new VarExp("v1")), new ValueExp(new IntValue(10)))),
                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v1"))),
                                        new CompStmt(new CountDownStmt("cnt"),
                        new ForkStmt(new CompStmt(new WriteHeapStmt("v2", new ArithExp('*', new ReadHeapExp(new VarExp("v2")),new ValueExp(new IntValue(10)))),
                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v2"))),
                                        new CompStmt(new CountDownStmt("cnt"),
                        new ForkStmt(new CompStmt(new WriteHeapStmt("v3", new ArithExp('*', new ReadHeapExp(new VarExp("v3")), new ValueExp(new IntValue(10)))),
                                new CompStmt(new PrintStmt(new ReadHeapExp(new VarExp("v3"))),
                                        new CountDownStmt("cnt")))))))))))),
                        new CompStmt(new AwaitStmt("cnt"),
                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),
                                        new CompStmt(new CountDownStmt("cnt"),
                                                new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))),
                                                        new EndStmt())))))))))))));

                ex11.typecheck(new MyDictionary<>());

                prg[last] = new PrgState(ex11);
                MyIRepository repo11 = new MyRepository(List.of(prg[last]), "log"+ last +".txt");
                ctr[last] = new MyController(repo11);
                codeTable.getItems().add(prg[last].getCode());
                last ++;

            } catch (MyException | IOException e) {
                System.out.println(e.toString());
            }






            once = false;
        }




    }
    @FXML
    void fillButton(ActionEvent event) throws IOException, MyException, InterruptedException {

        chosenCrtNr = (codeTable.getSelectionModel().getSelectedIndices().get(0) + 1);
        chosenNr = 0;
        String chosen = String.valueOf(chosenCrtNr);


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("program-window.fxml"));
        fxmlLoader.setController(this);
        Stage stage = new Stage();
        Scene scene = new Scene(fxmlLoader.load());



        stage.setTitle("Program " + chosen);
        stage.setScene(scene);
        stage.show();

        populatePrgTable();




        Stage mainStage = (Stage) codeTable.getScene().getWindow();
        mainStage.close();


    }


    @FXML
    void oneStep(ActionEvent event) throws MyException, IOException, InterruptedException {

        int nrOfProgrammes = ctr[chosenCrtNr].getAlivePrg().size();

        if(nrOfProgrammes != 0) {

            ctr[chosenCrtNr].oneStepForAllPrg(ctr[chosenCrtNr].getAlivePrg());

            if(ctr[chosenCrtNr].getAlivePrg().size() < nrOfProgrammes) {
                chosenNr = 0;
            }
        }

        if(nrOfProgrammes != 0) {

            try {
                populatePrgTable();
            }
            catch (IndexOutOfBoundsException ignored){
                prgIdView.getItems().clear();
                prgCounter.textProperty().set("Nr of running programmes: 0" );
            }

        }

    }

    private void populatePrgTable() throws InterruptedException, MyException, IOException {

        exeStack.getItems().clear();
        ArrayList<String> commands = new ArrayList<>(List.of(ctr[chosenCrtNr].repo.getPrg(chosenNr).exeStack.toString().split("; ")));

        commands.remove(commands.size() - 1);

        for (String i : commands)
            if (!Objects.equals(i, "")) exeStack.getItems().add(i + ";");


        heapView.getItems().clear();
        Set<Integer> addresses = prg[chosenCrtNr].heap.getContent().keySet();
        for (Integer i : addresses) {
            heapView.getItems().add(new TableItem(i.toString(), prg[chosenCrtNr].heap.readHeap(i).toString()));
        }



        latchTableView.getItems().clear();
        Set<Integer> latches = prg[chosenCrtNr].latchTable.getContent().keySet();
        for (Integer i : latches)
        {
            TableItem t = new TableItem(i.toString(), prg[chosenCrtNr].latchTable.readLatchTable(i).toString());
            latchTableView.getItems().add(t);
        }



        outView.getItems().clear();
        String[] out = prg[chosenCrtNr].out.toString().split("\n");
        outView.getItems().addAll(out);

        fileTableView.getItems().clear();
        String[] fileTable = prg[chosenCrtNr].fileTable.toString().split(",");
        fileTableView.getItems().addAll(fileTable);

        prgIdView.getItems().clear();
        String[] ids = ctr[chosenCrtNr].getPrgIds();
        prgIdView.getItems().addAll(ids);

        symTableView.getItems().clear();
        Map<String, Value> symTable = ctr[chosenCrtNr].repo.getPrg(chosenNr).getSymTable().getContent();
        for (String i : symTable.keySet())
        {
            symTableView.getItems().add(new TableItem(i,symTable.get(i).toString()));
        }

        prgCounter.textProperty().set("Nr of running programmes: " + String.valueOf(ctr[chosenCrtNr].getAlivePrg().size()));


    }



    @FXML
    void prgClicked(MouseEvent event) throws MyException, IOException, InterruptedException {
        chosenNr = prgIdView.getSelectionModel().getSelectedIndices().get(0);
        populatePrgTable();
    }

}