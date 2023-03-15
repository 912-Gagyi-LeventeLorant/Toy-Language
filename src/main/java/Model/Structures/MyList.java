package Model.Structures;

import java.util.ArrayList;
import java.util.List;

public class MyList<V> implements MyIList<V>{

    List<String> list = new ArrayList<String>();

    @Override
    public void output(String message) {
        list.add(message);
    }

    public String toString()
    {
        String s = "";
        for(Object s1 : list)
        {
            s = s + "\n" + s1.toString();
        }
        return s;
    }

}
