package Model.Expression;

import Exceptions.DeclarationException;
import Exceptions.MyException;
import Exceptions.WrongTypeException;
import Model.Statements.IStmt;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Structures.MyIStack;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

public class ReadHeapExp implements Exp {

    Exp exp;

    public ReadHeapExp(Exp e)
    {
        exp = e;
    }



    @Override
    public Value eval(MyIDictionary<String, Value> tbl, MyIHeap<Integer, Value> hp) throws MyException {



        Value var = exp.eval(tbl, hp);
        if(var.getType().equals(new RefType()))
        {
            RefValue value = (RefValue) var;
            int pos = value.getAddr();
            var = hp.readHeap(pos);
        }
        else throw new WrongTypeException("Value isn't RefValue");

        return var;
    }

    @Override
    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
            Type typ=exp.typecheck(typeEnv);
            if (typ instanceof RefType) {
                RefType reft =(RefType) typ;
                return reft.getInner();
            } else
                throw new MyException("the rH argument is not a Ref Type");
    }


    @Override
    public String toString() {
        return "rH(" + exp.toString() + ")";
    }
}
