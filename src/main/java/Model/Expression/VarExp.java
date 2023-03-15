package Model.Expression;


import Exceptions.DeclarationException;
import Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public class VarExp implements Exp{
    String id;

    public VarExp(String in)
    {
        id = in;
    }

    public String toString()
    {
        return id;
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        if(tbl.isDefined(id))
        {
        return tbl.lookup(id);
        }
        else
        {
            throw new DeclarationException("Variable not defined");
        }
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return typeEnv.lookup(id);
    }
    }

