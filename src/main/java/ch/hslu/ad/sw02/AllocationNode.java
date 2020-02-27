package ch.hslu.ad.sw02;

import ch.hslu.ad.sw01.Allocation;

public class AllocationNode {
    private final Allocation allocation;
    private AllocationNode child;

    AllocationNode(Allocation allocation, AllocationNode child) {
        this.allocation = allocation;
        this.child = child;
    }

    boolean hasNext() {
        return this.child != null;
    }

    AllocationNode next() {
        return this.child;
    }

    public Allocation get() {
        return this.allocation;
    }

    public void setChild(AllocationNode node) {
        this.child = node;
    }
}
