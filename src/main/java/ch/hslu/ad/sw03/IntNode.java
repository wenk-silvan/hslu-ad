package ch.hslu.ad.sw03;

public class IntNode<Integer> implements Comparable, Node {
    private Node left;
    private Node right;
    private Integer value;

    IntNode(Node left, Node right, Integer value) {
        this.left = left;
        this.right = right;
        this.value = value;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

    public Node left() {
        return this.left;
    }

    public Node right() {
        return this.right;
    }

    public Integer get() {
        return this.value;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
