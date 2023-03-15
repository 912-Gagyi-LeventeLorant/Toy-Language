package Model.Statements;

import Exceptions.MyException;
import Exceptions.WrongTypeException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

import java.io.IOException;

public class NewLatchStmt implements IStmt{

    String var;

    Exp exp;

    public NewLatchStmt(String v, Exp e)
    {
        var = v;
        exp = e;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if (symTbl.isDefined(var) && symTbl.lookup(var).getType().equals(new IntType())) {
            Value num1 = exp.eval(symTbl,heap);
            if(num1.getType().equals(new IntType()))
            {
                IntValue num11 = (IntValue) num1;
                state.latchTable.addNew(num11.getVal());
                symTbl.update(var, new IntValue(state.latchTable.lastAddress()));
                return null;
            }
            else throw new WrongTypeException("Expression is not type int");

        }
        else throw new WrongTypeException("Variable isn't of type int");

    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {

        Type typevar = typeEnv.lookup(var);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new IntType()) && typexp.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("NewLatch stmt: variable and/or expression are not of type int ");
    }

    @Override
    public String toString() {
        return "newLatch(" + var + "," + exp.toString() + ")";
    }


}
