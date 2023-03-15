package Model;

import Exceptions.MyException;
import Model.Statements.IStmt;
import Model.Structures.*;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.*;
import java.util.List;
import java.util.SplittableRandom;

public class PrgState{


    public MyIStack<IStmt> exeStack = new MyStack<IStmt>();

    static int all_id = 0;
    int id;
    String code;

    static synchronized void inc_id(){
        all_id++;

    }

    public PrgState(IStmt prg)
    {
        id = all_id;
        inc_id();
        exeStack.push(prg);
        code = getCode();
    }

    public IFileTable<String, BufferedReader> fileTable = new FileTable<>();

    public MyIHeap<Integer,Value> heap = new MyHeap<>();
    public MyIHeap<Integer, Value> getHeap(){return heap;};

    public MyILatchTable latchTable = new MyLatchTable();
    public MyILatchTable getLatchTable(){return latchTable;}



    public void addToFileTable(StringValue s) throws IOException {
        fileTable.addFile(s.getVal(), new BufferedReader(new FileReader(s.getVal())));
    }


    public MyIStack<IStmt> getStk()
    {
        return exeStack;
    };


    MyIDictionary<String, Value> symTable = new MyDictionary<>();

    public void setSymTable(MyIDictionary<String, Value> newSymTable){
        symTable = newSymTable;
    }
    public MyIDictionary<String, Value> getSymTable(){return symTable;};



    public MyIList<Value> out = new MyList<>();
    IStmt originalProgram; //optional field, but good to have
    PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value>
            ot, IStmt prg){
        exeStack=stk;
        symTable=symtbl;
        out = ot;
  //      originalProgram=deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
    }

    public String toString(){
        return "ID:" + id + " Execution Stack:" + exeStack.toString() + "Table of Symbols:" + symTable.toString() + "Output:" + out.toString() + "\nFile Table:" + fileTable.toString() +
                "Heap:" + heap.toString() ;
    }

    public String getId(){
        return id + "";
    }

    public String getCode(){
        return exeStack.toString();
    }

    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public PrgState oneStep() throws MyException, IOException {
        if(exeStack.isEmpty()) throw new MyException("prgstate stack is empty");
        IStmt  crtStmt = exeStack.pop();
        return crtStmt.execute(this);
    }



}
