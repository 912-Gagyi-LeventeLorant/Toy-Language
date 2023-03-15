package Model.Statements;

import Exceptions.DeclarationException;
import Exceptions.MyException;
import Exceptions.WrongTypeException;
import Model.Expression.Exp;
import Model.Expression.ValueExp;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReadFileStmt implements IStmt{

    Exp exp;

    String var_name;

    public ReadFileStmt(Exp e, String vn)
    {
        exp = e;
        var_name = vn;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> tbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();


        if(tbl.isDefined(var_name))
        {
            Value v = exp.eval(tbl,heap);
            if (v.getType().equals(new StringType())) {
                StringValue s = (StringValue) v;
                BufferedReader b = state.fileTable.getBuffer(s.getVal());
                String in = b.readLine();

                Exp iv;

                if(in == null) iv = new ValueExp(new IntValue(0));
                else iv = new ValueExp(new IntValue(Integer.parseInt(in)));


                tbl.update(var_name, iv.eval(tbl,heap));


            }
            else throw new WrongTypeException("Non-string value for file path");
        }
        else throw new DeclarationException(var_name.toString() + "is not defined");

        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }

    public String toString()
    {
        return "print(" + exp.toString() + ")";
    }
}
