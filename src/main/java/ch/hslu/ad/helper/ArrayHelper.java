package ch.hslu.ad.helper;

import java.util.Random;

public class ArrayHelper {
    public static char[] randomChars(final int length) {
        Random r = new Random();
        char[] arr = new char[length];

        String alphanumeric = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWQYZ";
        for (int i = 0; i < length; i++) {
            arr[i] = alphanumeric.charAt(r.nextInt(alphanumeric.length()));
        }
        return arr;
    }

    public static int[] randomInts(final int length) {
        Random r = new Random();
        int[] arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt();
        }
        return arr;
    }
}
