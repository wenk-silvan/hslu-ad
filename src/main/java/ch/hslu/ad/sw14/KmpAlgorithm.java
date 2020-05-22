package ch.hslu.ad.sw14;

public class KmpAlgorithm {
    private static int[] initNext(final String p) {
        final int m = p.length();
        final int[] next = new int[m];
        int i = 0;
        int j = -1;
        next[0] = -1;
        do {
            if ((j == -1) || (p.charAt(i) == p.charAt(j))) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        } while (i < (m - 1));
        return next;
    }

    public static void main(String[] args) {
        var array = initNext("EISBEIN");
        for (int i = 0; i < array.length; i++) {
            System.out.println(String.format("%s: %s", i, array[i]));
        }
    }
}
