package Model.Statements;

import Exceptions.MyException;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Types.BoolType;
import Model.Types.Type;

import java.io.IOException;

public class ForkStmt implements IStmt{

    IStmt stmt;

    public ForkStmt(IStmt stmt1)
    {
        stmt = stmt1;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        PrgState new_state = new PrgState(stmt);
        new_state.heap = state.heap;
        new_state.out = state.out;
        new_state.fileTable = state.fileTable;
        new_state.latchTable = state.latchTable;
        new_state.setSymTable(state.getSymTable().clone());

        return  new_state;

    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
            MyIDictionary<String, Type> copy = typeEnv.clone();
            stmt.typecheck(copy);
            return typeEnv;
    }

    @Override
    public String toString() {
        return "fork(" +
                stmt +
                ')';
    }
}
