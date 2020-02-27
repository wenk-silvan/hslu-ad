package ch.hslu.ad.sw01;

import java.util.Objects;

public final class Allocation implements Comparable {
    private final int blockSize;
    private final int startAddress;

    public Allocation(int blockSize, int address) {
        this.blockSize = blockSize;
        this.startAddress = address;
    }

    public int getBlockSize() {
        return this.blockSize;
    }

    public int getStartAddress() {
        return this.startAddress;
    }

    /**
     * Checks if given object equals this.
     * @param o the object to compare.
     * @return true, if the given object is equal and false, if not.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Allocation that = (Allocation) o;
        return Objects.equals(blockSize, that.blockSize) &&
                Objects.equals(startAddress, that.startAddress);
    }

    /**
     * Calculates the hashCode for this.
     * @return the hashCode
     */
    @Override
    public int hashCode() {
        int result = Objects.hash(startAddress);
        result = 31 * result + Objects.hashCode(blockSize);
        return result;
    }

    /**
     * Compares the startAdress of the given object with this.
     * @param o The object to compare.
     * @return a value lower than {@code 0} if the given object is smaller and a value higher than {@code 0} if it's bigger.
     */
    @Override
    public int compareTo(Object o) {
        if (getClass() != o.getClass()) throw new IllegalArgumentException("Object must be of type Allocation");
        Allocation a = (Allocation) o;
        return Integer.compare(a.startAddress, this.startAddress);
    }

    /**
     * Overrides the toString method.
     * @return
     */
    @Override
    public String toString() {
        return String.format("Allocation[Address:%s; Size: %s]", this.startAddress, this.blockSize);
    }
}
