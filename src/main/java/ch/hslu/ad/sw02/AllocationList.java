package ch.hslu.ad.sw02;

import ch.hslu.ad.sw01.Allocation;

public class AllocationList {
    private AllocationNode head;
    private int count = 1;

    public int count() {
        if (head == null) return 0;
        this.iterate(head);
        return count;
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

    void add(Allocation a) {
        var old = this.head;
        this.head = new AllocationNode(a, old);
    }

    int remove(int index) {
        this.iterate(this.head, index);
        return this.count;
    }

    AllocationNode getHead() {
        return this.head;
    }

    private boolean nextItemToBeRemoved(int index) {
        return index == this.count;
    }
}
