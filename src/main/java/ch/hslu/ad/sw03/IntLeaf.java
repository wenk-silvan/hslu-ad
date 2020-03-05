package ch.hslu.ad.sw03;

public class IntLeaf<Integer> implements Comparable, Node {
    private Integer value;

    IntLeaf(Integer value) {
        this.value = value;
    }

    public Integer get() {
        return this.value;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
