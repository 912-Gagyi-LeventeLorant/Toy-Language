package Model.Structures;

import java.util.Map;

public interface MyILatchTable {

    Integer readLatchTable(int id);

    void decrement(int id);

    void addNew(Integer v);

    int lastAddress();

    Map<Integer, Integer> getContent();


}
