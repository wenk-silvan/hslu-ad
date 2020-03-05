package ch.hslu.ad.sw03;

public interface Tree<T> {
    public Node search(T value);

    public void add(Node node);

    public Node getRoot();

    public void remove();
}
