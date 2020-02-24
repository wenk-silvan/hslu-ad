package ch.hslu.ad.sw01;

import java.util.Arrays;
import java.util.Objects;

public class Allocation implements Comparable {
    private final byte[] blockSize;
    private final int startAddress;

    public Allocation(byte[] blockSize, int address) {
        this.blockSize = blockSize;
        this.startAddress = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Allocation that = (Allocation) o;
        return Arrays.equals(blockSize, that.blockSize) &&
                Objects.equals(startAddress, that.startAddress);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(startAddress);
        result = 31 * result + Arrays.hashCode(blockSize);
        return result;
    }

    @Override
    public int compareTo(Object o) {
        if (getClass() != o.getClass()) throw new IllegalArgumentException("Object must be of type Allocation");
        Allocation a = (Allocation) o;
        return Integer.compare(a.startAddress, this.startAddress);
    }
}
