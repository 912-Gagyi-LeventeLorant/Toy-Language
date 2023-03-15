package Model.Structures;

import Exceptions.DeclarationException;
import Model.Structures.MyIDictionary;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MyDictionary<S,V> implements MyIDictionary<S,V> {
    Map<S, V> dictionary = new HashMap<>();


    @Override
    public boolean isDefined(S id) {
        if (dictionary.get(id) == null) return false;
        else return true;
    }
    @Override
    public V lookup(S id) throws DeclarationException {
        V out = dictionary.get(id);
        if(out != null) return dictionary.get(id);
        else throw new DeclarationException("Variable wasn't declared before");
    }

    @Override
    public void declare(S o, V o2) {
        dictionary.put(o,o2);
    }

    @Override
    public void update(S s, V v) {
        dictionary.remove(s);
        dictionary.put(s,v);
    }

    public String toString() {
        String s = "";

    for(Map.Entry<S,V> entry : dictionary.entrySet()) {

        s = s + entry.getKey() + " = " + entry.getValue() + ", ";
    }
    s = s +'\n';
    return s;
    }

    public Map<S, V> getContent() {
        return dictionary;
    }

    @Override
    public MyIDictionary<S, V> clone() {
        MyIDictionary<S,V> out = new MyDictionary<>();

        for(S k : dictionary.keySet()){
            try {
                out.declare(k,this.lookup(k));
            } catch (DeclarationException e) {
                throw new RuntimeException(e);
            }
        }

        return out;
    }
}
