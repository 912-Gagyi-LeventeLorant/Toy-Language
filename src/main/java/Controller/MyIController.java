package Controller;

import Exceptions.MyException;
import Model.PrgState;

import java.io.IOException;

public interface MyIController {


    void allStep() throws MyException, IOException, InterruptedException;


}
