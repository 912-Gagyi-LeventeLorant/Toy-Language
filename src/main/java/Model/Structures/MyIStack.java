package Model.Structures;

import java.util.Stack;

public interface MyIStack<T>{
    T pop();
    void push(T v);

    boolean isEmpty();

}
