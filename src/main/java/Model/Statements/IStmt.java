package Model.Statements;

import Exceptions.MyException;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Types.Type;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IStmt {
    PrgState execute(PrgState state) throws MyException, IOException;

    MyIDictionary<String, Type> typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;


}
