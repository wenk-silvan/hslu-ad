package ch.hslu.ad.sw02;

import ch.hslu.ad.sw01.Allocation;

import java.io.InvalidClassException;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class AllocationList {
    private AllocationNode head;
    private int count = 1;

    void add(Allocation a) {
        var old = this.head;
        this.head = new AllocationNode(a, old);
    }

    void addAll(List<Allocation> allocations) {
        for (Allocation a : allocations) {
            this.add(a);
        }
    }

    boolean contains(Object o) {
        if (o.getClass() != Allocation.class) {
            return false;
        }
        var a = (Allocation) o;
        return this.contains(this.head, a);
    }

    AllocationNode getHead() {
        return this.head;
    }

    Allocation poll() {
        Allocation head = this.head.get();
        this.remove(0);
        this.getAndResetCount();
        return head;
    }

    int remove(int index) {
        if (this.head == null) {
            return 0;
        }
        this.iterate(this.head, index);
        return this.getAndResetCount();
    }

    int size() {
        if (head == null) return 0;
        this.iterate(head);
        return count;
    }

    private boolean contains(AllocationNode node, Allocation a) {
        if (node.get().equals(a)) {
            return true;
        }
        else if (node.hasNext()) {
            return this.contains(node.next(), a);
        }
        return false;
    }

    private int getAndResetCount() {
        var count = this.count;
        this.count = 1;
        return count;
    }

    private void iterate(AllocationNode node) {
        this.iterate(node, -1);
    }

    /**
     * Iterates through the linked list, counts the list and removes item at index, if higher than -1.
     * @param node
     * @param index
     */
    private void iterate(AllocationNode node, int index) {
        if (index == 0) {
            this.head = null;
            if (node.hasNext()) {
                this.head = node.next();
                node = this.head;
            }
            index = -1;
        }

        if (this.nextItemToBeRemoved(index) && !node.next().hasNext()) {
            node.setChild(null);
        }

        if (node.hasNext()) {
            if (this.nextItemToBeRemoved(index)) {
                if (node.next().hasNext()) {
                    node.setChild(node.next().next());
                } else if (!node.next().hasNext()) {
                    node.setChild(null);
                }
                index = -1;
            }
            this.count++;
            this.iterate(node.next(), index);
        }
    }

    private boolean nextItemToBeRemoved(int index) {
        return index == this.count;
    }
}
