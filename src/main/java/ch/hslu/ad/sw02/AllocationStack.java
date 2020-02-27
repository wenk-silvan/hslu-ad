package ch.hslu.ad.sw02;

import ch.hslu.ad.sw01.Allocation;

public class AllocationStack {
    /**
     * Represents the stack of Allocations.
     */
    private Allocation[] allocations;

    public AllocationStack(int size) {
        this.allocations = new Allocation[size];
    }

    /**
     * Verifies weather the array is empty or not.
     * @return True if the array is empty, false if not.
     */
    boolean isEmpty() {
        return this.allocations.length == 0;
    }

    /**
     * Pushes a new element in front of the array.
     * @param a The allocation to push.
     * @return -1 if push failed, 1 if successful.
     */
    int push(Allocation a) {
        for(int i = this.allocations.length - 1; i >= 0; i--) {
            if (this.allocations[i] != null) {
                if (i == this.allocations.length - 1) {
                    return -1;
                }
                this.allocations[i + 1] = this.allocations[i];
            }
        }

        this.allocations[0] = a;
        return 1;
    }

    /**
     * Removes and returns the first element from the array.
     * @return The removed allocation.
     */
    public Allocation pop() {
        if (this.isEmpty()) {
            return null;
        }

        Allocation a = this.allocations[0];
        for(int i = this.allocations.length - 1; i >= 1; i--) {
            if (this.allocations[i] != null) {
                this.allocations[i - 1] = this.allocations[i];
            }
        }
        return a;
    }

    /**
     * Returns the length of the stack.
     * @return The length.
     */
    int size() {
        return this.allocations.length;
    }
}
