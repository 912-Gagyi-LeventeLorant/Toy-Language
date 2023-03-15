package Model.Statements;

import Exceptions.WrongTypeException;
import Model.Expression.Exp;
import Exceptions.MyException;
import Model.PrgState;
import Model.Structures.MyIDictionary;
import Model.Structures.MyIHeap;
import Model.Types.BoolType;
import Model.Types.Type;
import Model.Values.BoolValue;
import Model.Values.Value;

import java.io.FileNotFoundException;
import java.io.IOException;

public class IfStmt implements IStmt{
    Exp exp;
    IStmt thenS;
    IStmt elseS;

    public IfStmt(Exp e, IStmt t, IStmt el) {exp=e; thenS=t;elseS=el;}

    public String toString(){ return "if("+ exp.toString()+") then{" +thenS.toString()
            +"} else{"+elseS.toString()+"}";}

    public PrgState execute(PrgState state) throws MyException, IOException {
        MyIDictionary<String, Value> tbl = state.getSymTable();
        MyIHeap<Integer, Value> heap = state.getHeap();
        Value v = exp.eval(tbl,heap);
        if (v.getType().equals(new BoolType())) {
            BoolValue b = (BoolValue) v;
            if (b.getVal()) {
                thenS.execute(state);
            } else {
                elseS.execute(state);
            }
            return null;
        } else
        {
                throw new WrongTypeException("Not logic in if");
        }
    }

    @Override
    public MyIDictionary<String, Type> typecheck(MyIDictionary<String, Type> typeEnv) throws MyException {
        Type typexp=exp.typecheck(typeEnv);
        if (typexp.equals(new BoolType())) {
            MyIDictionary<String, Type> c1 = typeEnv.clone();
            MyIDictionary<String, Type> c2 = typeEnv.clone();
            thenS.typecheck(c1);
            elseS.typecheck(c2);
            return typeEnv;
        }
        else
            throw new MyException("The condition of IF has not the type bool");
    }
    }

