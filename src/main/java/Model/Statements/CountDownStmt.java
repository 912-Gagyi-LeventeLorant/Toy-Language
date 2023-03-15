package Model.Statements;

import Exceptions.MyException;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

import java.io.IOException;

public class CountDownStmt implements IStmt{

    String var;

    public CountDownStmt(String v)
    {
        var = v;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        MyIDictionary<String, Value> symTbl = state.getSymTable();

        if (symTbl.isDefined(var) && symTbl.lookup(var).getType().equals(new IntType())) {
            IntValue foundIndex = (IntValue)symTbl.lookup(var);
            Integer latchValue = state.latchTable.readLatchTable(foundIndex.getVal());
            if(latchValue == null) throw new MyException("Non existent latch value");
            else if(latchValue > 0) {
                state.latchTable.decrement(foundIndex.getVal());
                state.out.output(state.getId().toString());
                return null;
            }
            else{
                state.out.output(state.getId().toString());
                return null;
            }

        }


        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {

        Type typevar = typeEnv.lookup(var);
        if (typevar.equals(new IntType()))
            return typeEnv;
        else
            throw new MyException("CountDown stmt: variable is not of type int ");

    }



    @Override
    public String toString() {
        return "countDown(" + var + ")";
    }

}
