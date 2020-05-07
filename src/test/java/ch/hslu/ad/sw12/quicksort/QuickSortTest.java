package ch.hslu.ad.sw12.quicksort;

import ch.hslu.ad.helper.ArrayHelper;
import ch.hslu.ad.sw12.exercise.n4.quicksort.QuicksortRecursive;
import ch.hslu.ad.sw12.exercise.n4.quicksort.QuicksortTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuickSortTest {
    private static final Logger LOGGER = LogManager.getLogger(QuickSortTest.class);
    @Test
    void quickSortRecursiveTest() {
        int n = 1_000_000;
        int[] actual = ArrayHelper.randomInts(n, 100);
        int[] expected = Arrays.copyOf(actual, n);
        QuicksortRecursive.quicksort(actual);
        Arrays.sort(expected);
        for (int i = 0; i < n; i++) assertEquals(expected[i], actual[i]);
    }

    @Test
    void quickSortTaskTest() {
        int n = 1_000_000;
        int[] actual = ArrayHelper.randomInts(n, 100);
        int[] expected = Arrays.copyOf(actual, n);
        new QuicksortTask(actual).invoke();
        Arrays.sort(expected);
        //ArrayHelper.log(LOGGER, expected);
        //ArrayHelper.log(LOGGER, actual);
        for (int i = 0; i < n; i++) assertEquals(expected[i], actual[i]);
    }
}
