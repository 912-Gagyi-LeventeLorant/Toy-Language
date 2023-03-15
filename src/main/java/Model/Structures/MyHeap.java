package Model.Structures;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<K,V> implements MyIHeap<K,V> {

    Map<Integer, V> heap = new HashMap<>();

    int free = 1;

    @Override
    public void addNew(V v) {
        free = 1;
        while (heap.containsKey(free)) {
            free++;
        }
        heap.put(free, v);
    }

    @Override
    public V readHeap(int id) {
        return heap.get(id);
    }

    public int lastAddress() {
        return free;
    }

    ;

    @Override
    public void writeHeap(int s, V v) {
        heap.replace(s, v);
    }

    public String toString() {
        String s = "";

        for (Map.Entry<Integer, V> entry : heap.entrySet()) {

            s = s + entry.getKey() + " -> " + entry.getValue() + ", ";
        }
        s = s + '\n';
        return s;
    }

    public Map<Integer, V> getContent() {
        return this.heap;
    }


    public void setContent(Map<Integer, V> newHeap)
    {
           this.heap = newHeap;
    }

}
