package Model.Structures;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLatchTable implements MyILatchTable{

    Map<Integer, Integer> latchTable = new HashMap<>();

    Integer freeLoc = 1;

    Lock lock = new ReentrantLock();

    @Override
    public Integer readLatchTable(int id) {
        return latchTable.get(id);
    }

    @Override
    public void decrement(int id) {

        lock.lock();

        latchTable.replace(id,latchTable.get(id) - 1);

        lock.unlock();

    }

    @Override
    public void addNew(Integer v) {

        lock.lock();

        freeLoc = 1;
        while (latchTable.containsKey(freeLoc)) {
            freeLoc++;
        }
        latchTable.put(freeLoc, v);

        lock.unlock();


    }

    @Override
    public int lastAddress() {
        return freeLoc;
    }

    @Override
    public Map<Integer, Integer> getContent() {
        return latchTable;
    }

    public String toString() {
        String s = "";

        for (Map.Entry<Integer, Integer> entry : latchTable.entrySet()) {

            s = s + entry.getKey() + " -> " + entry.getValue() + ", ";
        }
        s = s + '\n';
        return s;
    }

}
