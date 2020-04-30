package ch.hslu.ad.sw11;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FixedSizeHeapTest {
    private static final Logger LOGGER = LogManager.getLogger(FixedSizeHeapTest.class);
    @Test
    void testHeap() {
        FixedSizeHeap heap = new FixedSizeHeap(10);
        heap.insert(1);
        heap.insert(7);
        heap.insert(5);
        heap.insert(3);
        heap.insert(4);
        heap.insert(11);
        heap.insert(75);
        heap.insert(2);
        heap.insert(8);
        heap.insert(13);
        assertEquals("[75 13 11 4 8 5 7 1 2 3 ]", heap.toString());
        heap.getMax();
        heap.getMax();
        heap.getMax();
        heap.getMax();
        heap.getMax();
        assertEquals("[5 4 2 1 3 0 0 0 0 0 ]", heap.toString());
    }
}
