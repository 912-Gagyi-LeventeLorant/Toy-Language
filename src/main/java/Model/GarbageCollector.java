package Model;

import Model.Values.RefValue;
import Model.Values.Value;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GarbageCollector {
    public static Map<Integer, Value> unsafeGarbageCollector(List<Integer> symTableAddr, Map<Integer, Value>
            heap){
        return heap.entrySet().stream()
                .filter(e->symTableAddr.contains(e.getKey())||heap.containsKey(e.getKey()))
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));}
    public static List<Integer> getAddrFromSymTable(Collection<Value> symTableValues){
        return symTableValues.stream()
                .filter(v-> v instanceof RefValue)
                .map(v-> {RefValue v1 = (RefValue)v; return v1.getAddr();})
                .collect(Collectors.toList());
    }
}
