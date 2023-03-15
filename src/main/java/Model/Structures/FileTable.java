package Model.Structures;

import java.util.HashMap;
import java.util.Map;

public class FileTable<S, F> implements IFileTable<S, F>{

    Map<S, F> fileTable = new HashMap<>();


    @Override
    public F getBuffer(S key) {
        return fileTable.get(key);
    }

    @Override
    public void addFile(S s, F f) {
        fileTable.put(s, f);
    }

    @Override
    public boolean contains(S s) {
        return fileTable.containsKey(s);
    }

    @Override
    public void removeFile(S s) {
        fileTable.remove(s);
    }


    public String toString() {
        String s = "";

        for(Map.Entry<S,F> entry : fileTable.entrySet()) {

            s = s + entry.getKey() + ", ";
        }
        s = s +'\n';
        return s;
    }


}
