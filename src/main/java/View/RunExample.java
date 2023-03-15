package View;

import Controller.MyController;
import Exceptions.MyException;

import java.io.IOException;

public class RunExample extends Command {
    private MyController ctr;
    public RunExample(String key, String desc,MyController ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {
        try{
            ctr.allStep(); }
        catch (MyException | IOException | InterruptedException error) {System.out.println(error.toString());} //here you must treat the exceptions that can not be solved in the controller
    }
}
