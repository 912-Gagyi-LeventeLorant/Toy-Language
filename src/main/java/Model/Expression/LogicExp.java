package Model.Expression;


import Exceptions.MyException;
import Exceptions.WrongTypeException;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

public class LogicExp implements Exp{
    Exp e1;
    Exp e2;
    int op; //1-and, 2-or

    String operation;

    public LogicExp(Exp in1, Exp in2, String c)
    {
        e1 = in1;
        e2 = in2;
        operation = c;
        if(c.equals("&&")) op = 1;
        else if (c.equals("||")) op = 2;
    }
    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        Value v1,v2;
        v1= e1.eval(tbl,hp);
        if (v1.getType().equals(new BoolType())) {
            v2 = e2.eval(tbl,hp);
            if (v2.getType().equals(new BoolType())) {
                BoolValue b1 = (BoolValue)v1;
                BoolValue b2 = (BoolValue)v2;
                boolean n1,n2;
                n1 = b1.getVal();
                n2 = b2.getVal();
                if (op==1) return new BoolValue(n1&&n2);
                if (op ==2) return new BoolValue(n1||n2);
            }else
                throw new WrongTypeException("second operand is not an integer");
        }else
            throw new WrongTypeException("first operand is not an integer");
        return null;
    }

    public Type typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typ1, typ2;
        typ1=e1.typecheck(typeEnv);
        typ2=e2.typecheck(typeEnv);
        if (typ1.equals(new BoolType())) {
            if (typ2.equals(new BoolType())) {
                return new BoolType();
            }
            else
                throw new MyException("second operand is not an boolean");
        }
        else
            throw new MyException("first operand is not an boolean");

    }


    public String toString()
    {
        return e1.toString() + op + e2.toString();
    }

 }
