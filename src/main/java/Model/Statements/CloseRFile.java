package Model.Statements;

import Exceptions.DeclarationException;
import Exceptions.MyException;
import Exceptions.WrongTypeException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CloseRFile implements IStmt{

    Exp exp;


    public CloseRFile(Exp e)
    {
        exp = e;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        MyIDictionary<String, Value> tbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        Value v = exp.eval(tbl,heap);
        if (v.getType().equals(new StringType())) {
            StringValue s = (StringValue) v;
            if (state.fileTable.contains(s.getVal())) {
                BufferedReader nb = state.fileTable.getBuffer(s.getVal());
                nb.close();
                state.fileTable.removeFile(s.getVal());
            }
            else {
                throw new DeclarationException("File path not declared");
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
        return "closeRFile(" + exp.toString() + ")";
    }
}
