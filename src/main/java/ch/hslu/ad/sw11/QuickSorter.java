package ch.hslu.ad.sw11;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

class QuickSorter {
    private static final Logger LOGGER = LogManager.getLogger(QuickSorter.class);

    static char[] quickSort(final char[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    static int[] quickSort(final int[] arr) {
        return quickSort(arr, 0, arr.length - 1);
    }

    static char[] quickSort(final char[] arr, final int left, final int right) {
        int up = left;
        int down = right - 1;
        char separator = arr[right];
        boolean allChecked = false;
        do {
            while (arr[up] < separator) {
                up++;
            }
            while ((arr[down] >= separator) && (down > up)) {
                down--;
            }
            if (down > up) {
                exchange(arr, up, down);
                up++;
                down--;
            } else {
                allChecked = true;
            }
            //log(arr);
        } while (!allChecked);
        exchange(arr, up, right);
        if (left < (up - 1)) quickSort(arr, left, (up - 1));
        if ((up + 1) < right) quickSort(arr, (up + 1), right);
        return arr;
    }

    static int[] quickSort(final int[] arr, final int left, final int right) {
        int up = left;
        int down = right - 1;
        int separator = arr[right];
        boolean allChecked = false;
        do {
            while (arr[up] < separator) {
                up++;
            }
            while ((arr[down] > separator) && (down > up)) {
                down--;
            }
            if (down > up) {
                exchange(arr, up, down);
                up++;
                down--;
            } else {
                allChecked = true;
            }
            //log(arr);
        } while (!allChecked);
        exchange(arr, up, right);
        if (left < (up - 1)) quickSort(arr, left, (up - 1));
        if ((up + 1) < right) quickSort(arr, (up + 1), right);
        return arr;
    }

    static char[] quickSortSwitchSame(final char[] arr) {
        final int left = 0;
        final int right = arr.length - 1;
        int up = left;
        int down = right - 1;
        char separator = arr[right];
        boolean allChecked = false;
        do {
            while (arr[up] < separator) {
                up++;
            }
            while ((arr[down] > separator) && (down > up)) {
                down--;
            }
            if (down > up) {
                exchange(arr, up, down);
                up++;
                down--;
            } else {
                allChecked = true;
            }
        } while (!allChecked);
        exchange(arr, up, right);
        if (left < (up - 1)) quickSort(arr, left, (up - 1));
        if ((up + 1) < right) quickSort(arr, (up + 1), right);
        return arr;
    }

    private static void exchange(final char[] arr, final int firstIndex, final int secondIndex) {
        char temp;
        temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

    private static void exchange(final int[] arr, final int firstIndex, final int secondIndex) {
        int temp;
        temp = arr[firstIndex];
        arr[firstIndex] = arr[secondIndex];
        arr[secondIndex] = temp;
    }

    private static void log(char[] arr) {
        var sb = new StringBuilder();
        for (char c : arr) sb.append(c).append(",");
        LOGGER.info(sb);
    }
}
