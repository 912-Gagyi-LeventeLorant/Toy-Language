package View;

import Controller.MyController;
import Model.Expression.ArithExp;
import Model.Expression.ReadHeapExp;
import Model.Expression.VarExp;
import Exceptions.MyException;
import Model.PrgState;
import Model.Statements.*;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;

import Model.Expression.ValueExp;
import Model.Values.StringValue;
import Repository.MyIRepository;
import Repository.MyRepository;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class MyView implements MyIView{


    IStmt ex1 = new CompStmt(new VarDeclStmt("v", new IntType()),
            new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

    IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()),
            new CompStmt(new VarDeclStmt("b",new IntType()),
            new CompStmt(new AssignStmt("a", new ArithExp('+',new ValueExp(new IntValue(2)),
                    new ArithExp('*',new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5))))),
            new CompStmt(new AssignStmt("b",new ArithExp('+',new VarExp("a"),
                    new ValueExp(new IntValue(1)))), new PrintStmt(new VarExp("b"))))));


    IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()),
            new CompStmt(new VarDeclStmt("v", new IntType()),
                    new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))),
                            new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new
                                    IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new
                                    VarExp("v"))))));


    IStmt ex5 = new CompStmt(new CompStmt(new OpenRFileStmt(new ValueExp(new StringValue("repo.txt"))),
            new CompStmt(new VarDeclStmt("v", new IntType()), new ReadFileStmt(new ValueExp(new StringValue("repo.txt")), "v"))), new PrintStmt(new VarExp("v")) );


    IStmt ex4 =new CompStmt(new VarDeclStmt("varf", new StringType()),
            new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("repo.txt"))),
                    new CompStmt( new OpenRFileStmt(new VarExp("varf")),
                            new CompStmt( new VarDeclStmt("varc", new IntType()),
                                    new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"),
                                            new CompStmt( new PrintStmt(new VarExp("varc")),
                                                    new CompStmt( new ReadFileStmt(new VarExp("varf"), "varc"),
                                                            new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFile(new VarExp("varf"))))) )))));

    IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())),
            new CompStmt(new AllocateHeapStmt("v",new ValueExp(new IntValue(20))), new PrintStmt(new ReadHeapExp(new VarExp("v")))));



    PrgState st1 = new PrgState(ex1);

    PrgState st2 = new PrgState(ex2);

    PrgState st3 = new PrgState(ex3);


    PrgState st4 = new PrgState(ex4);

    PrgState st6 = new PrgState(ex6);

    public MyView()
    {

    }

    public void start() throws MyException, IOException, InterruptedException {
        Scanner in = new Scanner(System.in);
        String s;

        System.out.print("Chose which program to execute:\n");
        System.out.print("1. int v; v=2;Print(v);\n");
        System.out.print("2. int a;int b; a=2+3*5;b=a+1;Print(b);\n");
        System.out.print("3. bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v);\n");


        s = in.nextLine();

        List<PrgState> prg = switch (s) {
            case "1" -> List.of(st1);
            case "2" -> List.of(st2);
            case "3" -> List.of(st3);
            case "4" -> List.of(st4);
            case "6" -> List.of(st6);
            default -> null;
        };

        MyIRepository m = new MyRepository(prg, "MyViewOut");

        MyController controller = new MyController(m);


        controller.allStep();
        }


    }


