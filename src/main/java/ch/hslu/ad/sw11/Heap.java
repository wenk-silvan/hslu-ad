package ch.hslu.ad.sw11;

public interface Heap<T> {
    void insert(T element);
    T getMax();
    void arrangeUp(int parentIndex);
    void arrangeDown(int childIndex);
}
