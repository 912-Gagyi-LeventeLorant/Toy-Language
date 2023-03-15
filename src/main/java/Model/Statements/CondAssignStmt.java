package Model.Statements;

import Exceptions.MyException;
import Exceptions.WrongTypeException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Structures.MyStack;
import Model.Types.BoolType;
import Model.Types.Type;

import java.io.IOException;

public class CondAssignStmt implements IStmt{

    String var;

    Exp cond, exp1, exp2;

    public CondAssignStmt(String v, Exp c, Exp e1, Exp e2)
    {
        var = v;
        cond = c;
        exp1 = e1;
        exp2 = e2;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {


        IStmt newState = new CompStmt(new IfStmt(cond, new AssignStmt(var, exp1), new AssignStmt(var, exp2)), state.exeStack.pop());

        state.exeStack = new MyStack<>();
        state.exeStack.push(newState);


        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {

        if(cond.typecheck(typeEnv).equals(new BoolType()))
        {
            if(exp1.typecheck(typeEnv).equals(exp2.typecheck(typeEnv)) && typeEnv.lookup(var).equals(exp2.typecheck(typeEnv)))
            {
                return typeEnv;
            }
            else throw new WrongTypeException("The three values are not of the same type");
        }
        else throw new WrongTypeException("Condition in conditional assignment isn't of type bool");

    }

    @Override
    public String toString() {
        return   var + "=" + "(" + cond + ")?" + exp1 + ":" + exp2;
    }
}
