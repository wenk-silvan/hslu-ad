package ch.hslu.ad.sw03;

public class SimpleNode<T> implements Node<T> {
    private SimpleNode<T> child;
    private T value;

    public SimpleNode(SimpleNode<T> child, T value) {
        this.child = child;
        this.value = value;
    }

    public boolean hasChild() {
        return this.child != null;
    }

    public SimpleNode<T> child() {
        return this.child;
    }

    public T get() {
        return this.value;
    }

    public void setChild(SimpleNode<T> node) {
        this.child = node;
    }

    @Override
    public String toString() {
        return String.format("Node[value: %s]", this.get());
    }
}
