package Model.Structures;

import java.util.Map;

public interface MyIHeap<K,V> {

    V readHeap(int id);

    void addNew(V v);

    void writeHeap(int s, V v);

    int lastAddress();

    Map<Integer, V> getContent();

    void setContent(Map<Integer, V> newHeap);
}
