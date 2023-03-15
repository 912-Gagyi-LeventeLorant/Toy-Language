package Model.Statements;

import Exceptions.MyException;
import Model.Expression.Exp;
import Model.Expression.VarExp;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.RefType;
import Model.Types.Type;
import Model.Values.RefValue;
import Model.Values.Value;

import java.io.IOException;

public class AllocateHeapStmt implements IStmt{

    String var;

    Exp exp;

    public AllocateHeapStmt(String varName, Exp v)
    {
        var = varName;
        exp = v;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if (symTbl.isDefined(var) && symTbl.lookup(var).getType().equals(new RefType())) {
            Value value = exp.eval(symTbl,heap);
            RefType ref = (RefType) symTbl.lookup(var).getType();
            if(value.getType().equals(ref.getInner()))
            {
                state.heap.addNew(value);
                symTbl.update(var, new RefValue(state.heap.lastAddress(), value.getType()));
            }

        }
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(var);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(new RefType(typexp)))
            return typeEnv;
        else
            throw new MyException("NEW stmt: right hand side and left hand side have different types ");
    }

    @Override
    public String toString() {
        return "new(" + var + "," + exp.toString() + ")";
    }




}
