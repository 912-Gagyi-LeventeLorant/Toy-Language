package Model.Statements;

import Exceptions.DeclarationException;
import Exceptions.WrongTypeException;
import Model.Expression.Exp;
import Exceptions.MyException;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Structures.MyIStack;
import Model.Types.Type;
import Model.Values.Value;

public class AssignStmt implements IStmt {
    String id;
    Exp exp;

    public AssignStmt(String in1, Exp in2)
    {
        id = in1;
        exp = in2;
    }


    public String toString() {
        return id + " = " + exp.toString();
    }

    public PrgState execute(PrgState state) throws MyException {
        MyIStack<IStmt> stk = state.getStk();
        MyIDictionary<String, Value> symTbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();

        if (symTbl.isDefined(id)) {
            Value val = exp.eval(symTbl,heap);
            Type typId = (symTbl.lookup(id)).getType();
            if (val.getType().equals(typId)) symTbl.update(id, val);
            else
                throw new WrongTypeException("declared type of variable" + id + " and type of the assigned expression do not match");
        } else throw new DeclarationException("the used variable" + id + " was not declared before");

        return null;


    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typevar = typeEnv.lookup(id);
        Type typexp = exp.typecheck(typeEnv);
        if (typevar.equals(typexp))
            return typeEnv;
        else
            throw new MyException("Assignment: right hand side and left hand side have different types ");
    }

}