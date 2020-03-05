package ch.hslu.ad.sw03;

public class IntNode implements Node {
    private Node left;
    private Node right;
    private Integer value;

    IntNode(Node left, Node right, Integer value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    boolean hasLeft() {
        return this.left != null;
    }

    boolean hasRight() {
        return this.right != null;
    }

    Node left() {
        return this.left;
    }

    Node right() {
        return this.right;
    }

    public Integer get() {
        return this.value;
    }

    void setLeft(Node node) {
        this.left = node;
    }

    void setRight(Node node) {
        this.right = node;
    }

    @Override
    public String toString() {
        return String.format("Node[value: %s]", this.get());
    }
}
