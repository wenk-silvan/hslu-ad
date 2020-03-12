package ch.hslu.ad.sw03;

public interface Tree<T> {
    public Node search(T value) throws TreeException;

    public void add(Node node) throws TreeException;

    public Node getRoot();

    public void remove(Node node);
}
