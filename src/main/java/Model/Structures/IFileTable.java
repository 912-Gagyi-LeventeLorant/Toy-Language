package Model.Structures;

public interface IFileTable<S, F> {

    F getBuffer(S key);

    void addFile(S s, F f);

    boolean contains(S s);

    void removeFile(S s);

}
