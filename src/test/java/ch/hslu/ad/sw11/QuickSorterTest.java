package ch.hslu.ad.sw11;

import ch.hslu.ad.helper.ArrayHelper;
import ch.hslu.ad.helper.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class QuickSorterTest {
    private static final Logger LOGGER = LogManager.getLogger(QuickSorterTest.class);

    @Test
    void testQuickSort() {
        char[] arr = {'b', 'z', 't', 'r', 'a', 'd', 'm', 'l'};
        char[] expected = {'a', 'b', 'd', 'l', 'm', 'r', 't', 'z'};
        char[] actual = QuickSorter.quickSort(arr);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void testQuickSortBorder() {
        char[] arr = {'b', 'z', 't', 'r', 'a', 'd', 'm', 'l'};
        char[] expected = {'a', 'b', 'd', 'l', 'm', 'r', 't', 'z'};
        char[] actual = QuickSorter.quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void testQuickSortSmall() {
        var randomCharArray = ArrayHelper.randomChars(100);
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
    }

    @Test
    void testQuickSortMedium() {
        var randomCharArray = ArrayHelper.randomChars(10_000);
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));

    }

    @Test
    void testQuickSortBig() {
        var randomCharArray = ArrayHelper.randomChars(500_000);
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
    }

    @Test
    void testQuickSortInt() {
        int[] arr = {5, 20, 1, 19191, -4, 40, 1, 3943, 5};
        int[] expected = {-4, 1, 1 ,5, 5, 20, 40, 3943, 19191};
        int[] actual = QuickSorter.quickSort(arr, 0, arr.length - 1);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void testQuickSortIntSmall() {
        var randomCharArray = ArrayHelper.randomInts(5_000);
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
    }

    @Test
    void testQuickSortIntMedium() {
        var randomCharArray = ArrayHelper.randomInts(100_000);
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));

    }

    @Test
    void testQuickSortIntBig() {
        var randomCharArray = ArrayHelper.randomInts(1_000_000);
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSort(randomCharArray));
    }

    @Test
    void testQuickSortImproved() {
        char[] arr = {'b', 'z', 't', 'r', 'a', 'd', 'm', 'l'};
        char[] expected = {'a', 'b', 'd', 'l', 'm', 'r', 't', 'z'};
        char[] actual = QuickSorter.quickSortSwitchSame(arr);
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], actual[i]);
        }
    }

    @Test
    void testQuickSortImprovedSmall() {
        var randomCharArray = ArrayHelper.randomChars(100);
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSortSwitchSame(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSortSwitchSame(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSortSwitchSame(randomCharArray));
    }

    @Test
    void testQuickSortImprovedMedium() {
        var randomCharArray = ArrayHelper.randomChars(10_000);
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSortSwitchSame(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSortSwitchSame(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSortSwitchSame(randomCharArray));

    }

    @Test
    void testQuickSortImprovedBig() {
        var randomCharArray = ArrayHelper.randomChars(500_000);
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSortSwitchSame(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSortSwitchSame(randomCharArray));
        Timer.stopWatchNano(LOGGER, f -> QuickSorter.quickSortSwitchSame(randomCharArray));
    }
}
