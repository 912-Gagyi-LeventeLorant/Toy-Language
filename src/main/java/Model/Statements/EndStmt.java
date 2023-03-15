package Model.Statements;

import Exceptions.MyException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Types.Type;

import java.io.IOException;

public class EndStmt implements IStmt{

    public EndStmt(){}

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "";
    }
}
