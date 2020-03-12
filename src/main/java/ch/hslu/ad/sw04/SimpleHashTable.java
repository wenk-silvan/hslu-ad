package ch.hslu.ad.sw04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class SimpleHashTable<T> implements HashTable<T> {
    private static final Logger LOGGER = LogManager.getLogger(SimpleHashTable.class);
    private T[] table;

    SimpleHashTable() {
        this.table = (T[]) new Integer[10];
    }

    @Override
    public boolean add(T element) {
        var hash = Objects.hashCode(element);
        if (this.table[hash] != null) {
            return false;
        }
        this.table[hash] = element;
        LOGGER.info("ADD: " + this.toString());
        return true;
    }

    @Override
    public boolean remove(T element) {
        var hash = Objects.hashCode(element);
        if (this.table[hash] == null) {
            return false;
        }
        this.table[hash] = null;
        LOGGER.info("REM: " + this.toString());
        return true;
    }

    @Override
    public T get(int index) {
        return this.table[index];
    }

    @Override
    public String toString() {
        return String.format("[%s][%s][%s][%s][%s][%s][%s][%s][%s][%s]",
                table[0], table[1], table[2], table[3], table[4], table[5], table[6], table[7], table[8], table[9]);
    }

}
