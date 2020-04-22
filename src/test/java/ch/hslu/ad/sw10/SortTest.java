package ch.hslu.ad.sw10;

import ch.hslu.ad.helper.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SortTest {
    private static final Logger LOG = LogManager.getLogger(SortTest.class);

    @Test
    void testInsertionSortShort() {
        int[] array = {3, 1, 5, 0, 19, -1, 4, 3};
        int[] expected = {-1, 0, 1, 3, 3, 4, 5, 19};
        var actual = (new Timer<int[]>()).stopWatch(LOG, () -> Sort.insertionSort(array));
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void testInsertionSortMedium() {
        var array = generateRandomArray(10000);
        var actual = (new Timer<int[]>()).stopWatch(LOG, () -> Sort.insertionSort(array));
    }

    @Test
    void testInsertionSortLong() {
        var array = generateRandomArray(100000);
        var actual = (new Timer<int[]>()).stopWatch(LOG, () -> Sort.insertionSort(array));
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
