package ch.hslu.ad.sw03;

public class IntLeaf implements Comparable, Node {
    private int value;

    IntLeaf(int value) {
        this.value = value;
    }

    public Integer get() {
        return this.value;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    @Override
    public String toString() {
        return String.format("Node[value: %s]", this.get());
    }
}
