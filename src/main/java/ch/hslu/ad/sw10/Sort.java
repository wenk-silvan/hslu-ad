package ch.hslu.ad.sw10;

class Sort {
    static int[] insertionSort(final int[] array) {
        int element;
        int j;
        for (int i = 1; i < array.length; i++) {
         element = array[i];
         j = i;
         while ((j > 0) && array[j - 1] > element) {
             array[j] = array[j - 1];
             j--;
         }
         array[j] = element;
        }
        return array;
    }

    static int[] selectionSort(final int[] array) {
        int indexOfSmallest;
        for (int i = 0; i < array.length; i++) {
            indexOfSmallest = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[indexOfSmallest]) {
                    indexOfSmallest = j;
                }
            }
            var cache = array[i];
            array[i] = array[indexOfSmallest];
            array[indexOfSmallest] = cache;
        }
        return array;
    }

    static int[] bubbleSort(final int[] array) {
        int current;
        for (int i = array.length; i >= 0; i--) {
            current = 0;
            for (int next = 1; next < i; next++) {
                if (array[current] > array[next]) {
                    var cache = array[current];
                    array[current] = array[next];
                    array[next] = cache;
                }
                current++;
            }
        }
        return array;
    }
}
