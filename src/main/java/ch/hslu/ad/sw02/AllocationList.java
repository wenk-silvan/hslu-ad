package ch.hslu.ad.sw02;

import ch.hslu.ad.sw01.Allocation;

import java.util.List;
import java.util.Objects;

public class AllocationList {
    private AllocationNode head;
    private int count = 1;

    void add(Allocation a) {
        var old = this.head;
        this.head = new AllocationNode(a, old);
    }

    void add(List<Allocation> allocations) {
        for (Allocation a : allocations) {
            this.add(a);
        }
    }

    boolean contains(Allocation a) {
        return this.contains(this.head, a);
    }

    int count() {
        if (head == null) return 0;
        this.iterate(head);
        return count;
    }

    AllocationNode getHead() {
        return this.head;
    }

    int remove(int index) {
        if (this.head == null) {
            return 0;
        }
        this.iterate(this.head, index);
        return this.count;
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

    private void iterate(AllocationNode node) {
        this.iterate(node, -1);
    }

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
