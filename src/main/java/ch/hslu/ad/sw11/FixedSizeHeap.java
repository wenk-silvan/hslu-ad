package ch.hslu.ad.sw11;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class FixedSizeHeap implements Heap<Integer> {
    private static final Logger LOGGER = LogManager.getLogger(FixedSizeHeap.class);
    private int size;
    private int[] heap;
    private int tail;

    public FixedSizeHeap(int size) {
        this.size = size;
        this.heap = new int[size];
        this.tail = 0;
    }

    @Override
    public void insert(Integer element) {
        LOGGER.info("insert " + element + ": " + this.toString());
        heap[tail] = element;
        arrangeUp(tail);
        if (tail < heap.length - 1) tail++;
    }

    @Override
    public Integer getMax() {
        LOGGER.info("getMax: " + this.toString());
        var head = heap[0];
        heap[0] = heap[tail];
        heap[tail] = 0;
        arrangeDown(0);
        tail--;
        return head;
    }

    @Override
    public void arrangeDown(int parentIndex) {
        int leftChildIndex = (parentIndex * 2) + 1;
        int rightChildIndex = (parentIndex + 1) * 2;
        int swapIndex;
        if (leftChildIndex >= heap.length && rightChildIndex >= heap.length) return;
        if (leftChildIndex < heap.length) {
            if (rightChildIndex < heap.length) {
                if (heap[leftChildIndex] >= heap[rightChildIndex]) swapIndex = leftChildIndex;
                else swapIndex = rightChildIndex;
            } else swapIndex = leftChildIndex;
            compareAndSwap(swapIndex, parentIndex);
            arrangeDown(swapIndex);
        } else {
            compareAndSwap(rightChildIndex, parentIndex);
            arrangeDown(rightChildIndex);
        }
    }

    @Override
    public void arrangeUp(int childIndex) {
        int parentIndex = (childIndex - 1) / 2;
        if (childIndex == 0) return;
        compareAndSwap(childIndex, parentIndex);
        arrangeUp(parentIndex);
    }

    private void compareAndSwap(int a, int b) {
        if (heap[a] > heap[b]) {
            int old = heap[b];
            heap[b] = heap[a];
            heap[a] = old;
            LOGGER.info("swapped: " + this.toString());
        }
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("[");
        for (int i : heap) sb.append(i).append(' ');
        sb.append("]");
        return sb.toString();
    }
}
