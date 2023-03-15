package Model.Structures;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

public class MyStack<T> implements MyIStack<T>{
    Stack<T> stack = new Stack<T>();

    @Override
    public T pop()
    {
        return stack.pop();
    };

    @Override
    public void push(T v)
    {
        stack.push(v);
    }

    @Override
    public boolean isEmpty() {
        if (stack.isEmpty()) return true;
        else return false;
    }

    @Override
    public String toString()
    {
        String s = "";
        for(T s1 : stack)
        {
            s = s + s1.toString();
        }
    return s + '\n';
    }


}
