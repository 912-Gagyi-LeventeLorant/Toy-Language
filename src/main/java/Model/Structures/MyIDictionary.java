package Model.Structures;

import Exceptions.DeclarationException;

import java.util.Dictionary;
import java.util.Map;

public interface MyIDictionary<S, V>{

    boolean isDefined(S id);
    V lookup(S id) throws DeclarationException;

    void declare(S s, V v);

    void update(S s, V v);

    Map<S, V> getContent();

    MyIDictionary<S, V> clone();
}
