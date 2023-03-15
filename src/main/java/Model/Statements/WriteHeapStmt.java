package Model.Statements;

import Exceptions.DeclarationException;
import Exceptions.MyException;
import Exceptions.WrongTypeException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.StringValue;
import Model.Values.Value;

import java.io.IOException;

public class WriteHeapStmt implements IStmt{

    String var;

    Exp expression;

    public WriteHeapStmt(String v, Exp e)
    {
        var = v;
        expression = e;
    }


    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if(symTbl.isDefined(var) && symTbl.lookup(var).getType().equals(new RefType()))
        {
            RefValue variable = (RefValue) symTbl.lookup(var);
            if(variable.getLocationType().equals(expression.eval(symTbl,heap).getType()))
                if(heap.readHeap(variable.getAddr()) != null)
                {
                    heap.writeHeap(variable.getAddr(),expression.eval(symTbl,heap));
                }
                else throw new DeclarationException("Address wasn't declared before");
            else throw new WrongTypeException("New value and old value not the same type");
        }
        else throw new DeclarationException("Variable not defined or not defined as a reference");



        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        expression.typecheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "Wh(" + var + ',' + expression + ')';
    }
}
