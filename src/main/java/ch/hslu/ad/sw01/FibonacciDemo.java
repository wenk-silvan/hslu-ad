package ch.hslu.ad.sw01;

public class FibonacciDemo {
    static Long[] numbers = {};

    /**
     * Calculates the fibonacci value of the given index n recursively.
     * @param n is the fibonacci index
     * @return the calculated fibonacci value.
     */
    static long fiboRec1(int n) {
        if (n <= 2) {
            return (n > 0) ? 1 : 0; // Rekursionsbasis
        }
        return fiboRec1(n - 1) + fiboRec1(n - 2); // Rekursionsvorschrift
    }

    /**
     * Calculates the fibonacci value of the given index n iteratively.
     * @param n is the fibonacci index
     * @return the calculated fibonacci value.
     */
    static long fiboIter(int n) {
        int prePreviousValue = 0;
        int previousValue = 0;
        int value = 0;
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
            } else if (i == 1) {
                value = 1;
            } else if (i == 2) {
                previousValue = value;
                value = 1;
            } else {
                prePreviousValue = previousValue;
                previousValue = value;
                value = previousValue + prePreviousValue;
            }
        }
        return value;
    }
}
