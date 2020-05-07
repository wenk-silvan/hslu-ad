package ch.hslu.ad.helper;

import java.util.Random;
import org.apache.logging.log4j.Logger;

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
        return randomInts(length, Integer.MAX_VALUE);
    }

    public static int[] randomInts(final int length, int bound) {
        Random r = new Random();
        int[] arr = new int[length];

        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt(bound);
        }
        return arr;
    }

    public static void log(Logger log, char[] arr) {
        var sb = new StringBuilder();
        for (char c : arr) sb.append(c).append(",");
        log.info(sb.toString());
    }

    public static void log(Logger log, int[] arr) {
        var sb = new StringBuilder();
        for (int i : arr) sb.append(i).append(",");
        log.info(sb.toString());
    }
}
