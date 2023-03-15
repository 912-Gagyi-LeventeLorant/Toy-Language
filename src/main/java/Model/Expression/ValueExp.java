package Model.Expression;

import Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public class ValueExp implements Exp{
    Value e;

    public ValueExp(Value in)
    {
        e = in;
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {return e;}

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        return e.getType();
    }

    public String toString()
    {
        return e.toString();
    }
 }