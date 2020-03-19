package ch.hslu.ad.sw04;

import ch.hslu.ad.sw03.Node;
import ch.hslu.ad.sw03.SimpleNode;
import ch.hslu.ad.sw03.TreeException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class BucketsHashTable<T> implements HashTable<T> {
    private static final Logger LOGGER = LogManager.getLogger(SimpleHashTable.class);
    private final SimpleNode<T>[] table;

    BucketsHashTable(int size) {
        this.table = new SimpleNode[size];
    }

    @Override
    public boolean add(T element) {
        var hash = this.getPosition(element);
        if (this.table[hash] != null) {
            this.appendNode(this.table[hash], element);
        }
        else {
            this.table[hash] = new SimpleNode<T>(null, element);
        }
        LOGGER.info("ADD: " + this.toString());
        return true;
    }

    @Override
    public boolean remove(T element) {
        var hash = this.getPosition(element);
        if (this.table[hash] == null) {
            throw new HashTableException("The element " + element.toString() + " was not found.");
        }
        else if (this.table[hash].get() == element) {
            if (!this.table[hash].hasChild()) {
                this.table[hash] = null;
            }
            else {
                this.table[hash] = this.table[hash].child();
            }
        }
        else {
            this.removeNode(this.table[hash], element);
        }
        LOGGER.info("ADD: " + this.toString());
        return true;
    }

    @Override
    public T get(T element) {
        var hash = this.getPosition(element);

        if (this.table[hash] == null) {
            throw new HashTableException("The element " + element.toString() + " was not found.");
        }
        return this.findNode(this.table[hash], element);
    }

    SimpleNode<T> getByIndex(int index) {
        return this.table[index];
    }

    private void appendNode(SimpleNode<T> node, T element) {
        if (node.get() == element) {
            throw new HashTableException("The element " + element.toString() + " already exists.");
        }
        if (node.hasChild()) {
            this.appendNode(node.child(), element);
        }
        node.setChild(new SimpleNode<T>(null, element));
    }

    private T findNode(SimpleNode<T> node, T element) {
        if (node.get() == element) {
            return element;
        }
        if (node.hasChild()) {
            return this.findNode(node.child(), element);
        }
        throw new HashTableException("The element " + element.toString() + " was not found.");
    }

    private int getPosition(T element) {
        return element.hashCode() % 10;
    }

    private void removeNode(SimpleNode<T> node, T element) {
        if (!node.hasChild()) {
            throw new HashTableException("The element " + element.toString() + " was not found.");
        }
        else if (node.child().get() != element) {
            this.removeNode(node.child(), element);
        }
        if (node.child().hasChild()) {
            node.setChild(node.child().child());
        }
        else {
            node.setChild(null);
        }
    }
}
