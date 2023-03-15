package Model.Expression;

import Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public interface Exp {
    Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException;

    Type  typecheck(MyIDictionary<String,Type> typeEnv) throws MyException;
}