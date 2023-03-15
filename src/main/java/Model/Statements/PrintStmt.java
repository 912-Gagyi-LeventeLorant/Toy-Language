package Model.Statements;

import Model.Expression.Exp;
import Model.Expression.ReadHeapExp;
import Model.Expression.ValueExp;
import Model.Expression.VarExp;
import Exceptions.MyException;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.Type;
import Model.Values.Value;

public class PrintStmt implements IStmt{
    Exp exp;

    public PrintStmt(VarExp v) {
        exp = v;
    }

    public PrintStmt(ValueExp v) {
        exp = v;
    }

    public PrintStmt(ReadHeapExp v) {
        exp = v;
    }

    @Override
    public String toString() {
     return "print(" +exp.toString()+")";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIDictionary<String, Value> tbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        state.out.output(exp.eval(tbl,heap).toString());
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        exp.typecheck(typeEnv);
        return typeEnv;
    }
}