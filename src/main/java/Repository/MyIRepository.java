package Repository;

import Exceptions.MyException;
import Model.PrgState;

import java.util.List;

public interface MyIRepository {


    List<PrgState> getPrgList();

    void setPrgList(List<PrgState> newList);

    void logPrgStateExec(PrgState prgState) throws MyException;

    String[] getIds();

    PrgState getPrg(int i);

}
