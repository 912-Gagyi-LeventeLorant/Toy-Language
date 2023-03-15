package Model.Expression;

import Exceptions.DivisionByZeroException;
import Exceptions.MyException;
import Exceptions.WrongTypeException;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.Value;

public class RelationExp implements Exp{

    Exp e1;
    Exp e2;
    int op; //1-plus, 2-minus, 3-star, 4-divide

    String operation;

    public RelationExp(Exp exp1, Exp exp2, String oper)
    {
        e1 = exp1;
        e2 = exp2;
        op = switch (oper)
                {
                    case "==" -> 1;
                    case "!=" -> 2;
                    case "<" -> 3;
                    case "<=" -> 4;
                    case ">" -> 5;
                    case ">=" -> 6;
                    default -> 0;
                };
        operation = oper;
    }

    @Override
    public Value eval(MyIDictionary<String,Value> tbl, MyIHeap<Integer,Value> hp) throws MyException {
        Value v1,v2;
        v1 = e1.eval(tbl,hp);
        if (v1.getType().equals(new IntType())) {
            v2 = e2.eval(tbl,hp);
            if (v2.getType().equals(new IntType())) {
                IntValue i1 = (IntValue)v1;
                IntValue i2 = (IntValue)v2;
                int n1,n2;
                n1 = i1.getVal();
                n2 = i2.getVal();
                if(op==1) return new BoolValue(n1==n2);
                if(op==2) return new BoolValue(n1!=n2);
                if(op==3) return new BoolValue(n1<n2);
                if(op==4) return new BoolValue(n1<=n2);
                if(op==5) return new BoolValue(n1>n2);
                if(op==6) return new BoolValue(n1>=n2);
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
        if (typ1.equals(new IntType())) {
            if (typ2.equals(new IntType())) {
                return new BoolType();
            }
            else
                throw new MyException("second operand is not an integer");
        }
        else
            throw new MyException("first operand is not an integer");

    }


    public String toString()
    {
        return e1.toString() + operation + e2.toString();
    }

}
