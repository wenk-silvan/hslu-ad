package ch.hslu.ad.sw04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Stream;

public final class SimpleHashTable<T> implements HashTable<T> {
    private static final Logger LOGGER = LogManager.getLogger(SimpleHashTable.class);
    private final T[] table;
    private final T tombstone;

    SimpleHashTable(T tombstone) {
        this.table = (T[]) new Object[10];
        this.tombstone = tombstone;
    }

    @Override
    public boolean add(T element) {
        if (this.isFull() || this.isDuplicate(element)) {
            return false;
        }
        var hash = this.getPosition(element);
        if (this.table[hash] != null) {
            var index = (hash == 9) ? 0 : hash + 1;
            while(this.table[index] != null) {
                index = (index == 10) ? 0 : index + 1;
            }
            this.table[index] = element;
        }
        else {
            this.table[hash] = element;
        }
        LOGGER.info("ADD: " + this.toString());
        return true;
    }

    @Override
    public boolean remove(T element) {
        var hash = this.getPosition(element);
        if (this.table[hash] == null) {
            return false;
        }
        var index = hash;
        int indexToRemove = -1;
        if (this.table[hash] != null) {
            if (this.table[hash].equals(element)) {
                indexToRemove = index;
            }
            else {
                while(this.table[index] != null && index != hash - 1) {
                    index = (index == 10) ? 0 : index + 1;
                    if (this.table[index] != null && this.table[index].equals(element)) {
                        indexToRemove = index;
                    }
                }
            }
        }
        if (indexToRemove == -1) {
            return false;
        }
        this.table[indexToRemove] = this.tombstone;
        LOGGER.info("REM: " + this.toString());
        return true;
    }

    @Override
    public T get(T element) {
        var hash = this.getPosition(element);
        var index = hash;
        if (this.table[hash] != null) {
            if (this.table[hash].equals(element)) {
                return this.table[hash];
            }
            while(this.table[index] != null && index != hash - 1) {
                index = (index == 10) ? 0 : index + 1;
                if (this.table[index].equals(element)) {
                    return this.table[index];
                }
            }
        }
        throw new HashTableException("The element " + element.toString() + " was not found.");
    }

    @Override
    public String toString() {
        return String.format("[%s][%s][%s][%s][%s][%s][%s][%s][%s][%s]",
                table[0], table[1], table[2], table[3], table[4], table[5], table[6], table[7], table[8], table[9]);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SimpleHashTable<?> that = (SimpleHashTable<?>) o;
        return Arrays.equals(table, that.table) &&
                Objects.equals(tombstone, that.tombstone);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(tombstone);
        result = 31 * result + Arrays.hashCode(table);
        return result;
    }

    T getByIndex(int index) {
        return this.table[index];
    }

    private boolean isDuplicate(T element) {
        return Arrays.asList(this.table).contains(element);
    }

    private boolean isFull() {
        for (T t : this.table) {
            if (t == null) {
                return false;
            }
        }
        return true;
    }

    private int getPosition(T element) {
        return element.hashCode() % 10;
    }
}
