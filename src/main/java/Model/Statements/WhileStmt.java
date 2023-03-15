package Model.Statements;

import Exceptions.MyException;
import Exceptions.WrongTypeException;
import Model.Expression.Exp;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.io.IOException;

public class WhileStmt implements IStmt {

    Exp exp;

    IStmt thenS;


    public WhileStmt(Exp e, IStmt t) {
        exp = e;
        thenS = t;
    }

    public String toString() {
        return "while(" + exp.toString() + ") {" + thenS.toString() + ";}";
    }

    @Override
    public PrgState execute(PrgState state) throws MyException, IOException {

        MyIDictionary<String, Value> tbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        Value v = exp.eval(tbl, heap);
        if (v.getType().equals(new BoolType())) {
            BoolValue b = (BoolValue) v;
            if (b.getVal()) {
                state.exeStack.push(this);
                thenS.execute(state);
            }
            return null;
        }
        else {
            throw new WrongTypeException("Not logic in while");
        }

    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typecheck(typeEnv);
        MyIDictionary<String, Type> copy = typeEnv.clone();
        if (typexp.equals(new BoolType())) {
            thenS.typecheck(copy);
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }

}
