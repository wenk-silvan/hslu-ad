package ch.hslu.ad.sw10;

public class Sort {
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
}
