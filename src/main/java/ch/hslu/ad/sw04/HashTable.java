package ch.hslu.ad.sw04;

import java.util.ArrayList;

public interface HashTable<T> {
    public boolean add(T element);
    public boolean remove(T element);
    public T get(T element);
}
