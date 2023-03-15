package Model.Statements;

import Exceptions.DeclarationException;
import Exceptions.MyException;
import Exceptions.WrongTypeException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.BoolType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class OpenRFileStmt implements IStmt {

    Exp exp;

    public OpenRFileStmt(Exp in) {
        exp = in;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, FileNotFoundException {

        MyIDictionary<String, Value> tbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        Value v = exp.eval(tbl,heap);
        if (v.getType().equals(new StringType())) {
            StringValue s = (StringValue) v;
            if (!state.fileTable.contains(s.getVal())) {
                BufferedReader nb = new BufferedReader(new FileReader(s.getVal()));
                state.fileTable.addFile(s.getVal(), nb);
            }
            else {
            throw new DeclarationException("File path already declared");
            }
        }
        else throw new WrongTypeException("Non-string value for file path");
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    public String toString()
    {
        return "readFile(" + exp.toString() + ")";
    }

}
