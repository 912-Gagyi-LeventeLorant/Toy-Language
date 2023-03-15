package Model.Statements;

import Exceptions.MyException;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIStack;
import Model.PrgState;
import Model.Types.Type;

public class CompStmt implements IStmt {
    public IStmt first;
    public IStmt snd;

    public CompStmt(IStmt v, IStmt v1) {
        first = v;
        snd = v1;
    }


    @Override
    public String toString() {
        return first.toString() + "; " + snd.toString() ;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        stk.push(snd);
        stk.push(first);
        return null;
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
            //MyIDictionary<String,Type> typEnv1 = first.typecheck(typeEnv);
            //MyIDictionary<String,Type> typEnv2 = snd.typecheck(typEnv1);
            //return typEnv2;
            return snd.typecheck(first.typecheck(typeEnv));
    }
    }
