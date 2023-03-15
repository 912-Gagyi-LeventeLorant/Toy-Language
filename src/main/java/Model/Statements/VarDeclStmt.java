package Model.Statements;

import Exceptions.DeclarationException;
import Exceptions.MyException;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Structures.MyIStack;
import Model.Types.Type;
import Model.Values.Value;

public class VarDeclStmt implements IStmt{

    String id;

    Type type;

    public VarDeclStmt(String in1, Type in2)
    {
        id = in1;
        type = in2;
    }


    public String toString() {
        return type.toString()  + ' ' + id;
    }

    @Override
    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if (!symTbl.isDefined(id)) {
            symTbl.declare(id,type.returnNewValue());
        } else throw new DeclarationException("the used variable" + id + " was already declared before");

        return null;


    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        typeEnv.declare(id,type);
        return typeEnv;
    }
}
