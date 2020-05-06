package ch.hslu.ad.sw10;

import ch.hslu.ad.helper.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SortTest {
    private static final Logger LOG = LogManager.getLogger(SortTest.class);
    private final int[] shortArray = generateRandomArray(50_000);
    private final int[] mediumArray = generateRandomArray(100_000);
    private final int[] longArray = generateRandomArray(200_000);

    @Test
    void testInsertionSortShort() {
        int[] expected = {-1, 0, 1, 3, 3, 4, 5, 19};
        var actual = (new Timer<int[]>()).stopWatch(LOG, () -> Sort.insertionSort(this.shortArray));
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void testInsertionSortMedium() {
        var actual = (new Timer<int[]>()).stopWatch(LOG, () -> Sort.insertionSort(this.mediumArray));
    }

    @Test
    void testInsertionSortLong() {
        var actual = (new Timer<int[]>()).stopWatch(LOG, () -> Sort.insertionSort(this.longArray));
    }

    @Test
    void testSelectionSortShort() {
        int[] expected = {-1, 0, 1, 3, 3, 4, 5, 19};
        var actual = (new Timer<int[]>()).stopWatch(LOG, () -> Sort.selectionSort(this.shortArray));
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void testSelectionSortMedium() {
        var actual = (new Timer<int[]>()).stopWatch(LOG, () -> Sort.selectionSort(this.mediumArray));
    }

    @Test
    void testSelectionSortLong() {
        var actual = (new Timer<int[]>()).stopWatch(LOG, () -> Sort.selectionSort(this.longArray));
    }

    @Test
    void testBubbleSortShort() {
        int[] expected = {-1, 0, 1, 3, 3, 4, 5, 19};
        var actual = (new Timer<int[]>()).stopWatch(LOG, () -> Sort.bubbleSort(this.shortArray));
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void testBubbleSortMedium() {
        var actual = (new Timer<int[]>()).stopWatch(LOG, () -> Sort.bubbleSort(this.mediumArray));
    }

    @Test
    void testBubbleSortLong() {
        var actual = (new Timer<int[]>()).stopWatch(LOG, () -> Sort.bubbleSort(this.longArray));
    }

    private int[] generateRandomArray(int n){
        var list = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++)
        {
            list[i] = random.nextInt(Integer.MAX_VALUE);
        }
        return list;
    }
}
