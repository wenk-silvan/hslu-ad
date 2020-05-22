package ch.hslu.ad.sw14;

public class Quicksearch {
    public static int quickSearch(final String text, final String pattern) {
        final int lengthOfText = text.length();
        final int lengthOfPattern = pattern.length();
        final int range = 256;
        final int[] shift = new int[range];

        for (int i = 0; i < range; i++) {
            shift[i] = lengthOfPattern + 1;
        }
        for (int i = 0; i < lengthOfPattern; i++) {
            shift[pattern.charAt(i)] = lengthOfPattern - i;
        }

        int indexOfText = 0;
        int indexOfPattern = 0;
        do {
            if (text.charAt(indexOfText + indexOfPattern) == pattern.charAt(indexOfPattern)) {
                indexOfPattern++;
            } else  {
                if ((indexOfText + lengthOfPattern) < lengthOfText) {
                    indexOfText += shift[text.charAt(indexOfText + lengthOfPattern)];
                    indexOfPattern = 0;
                } else {
                    break;
                }
            }
        } while ((indexOfPattern < lengthOfPattern) && ((indexOfText + lengthOfPattern) <= lengthOfText));
        if (indexOfPattern == lengthOfPattern) {
            return indexOfText;
        } else {
            return -1;
        }
    }

    public static int optimalMismatch(final String text, final String pattern) {
        final int lengthOfText = text.length();
        final int lengthOfPattern = pattern.length();
        final int range = 256;
        final int[] shift = new int[range];

        for (int i = 0; i < range; i++) {
            shift[i] = lengthOfPattern + 1;
        }
        for (int i = 0; i < lengthOfPattern; i++) {
            shift[pattern.charAt(i)] = lengthOfPattern - i;
        }

        int indexOfText = 0;
        int indexOfPattern = 0;
        do {
            if (text.charAt(indexOfText + indexOfPattern) == pattern.charAt(indexOfPattern)) {
                indexOfPattern++;
            } else  {
                if ((indexOfText + lengthOfPattern) < lengthOfText) {
                    int elementIndex = text.charAt(indexOfText + lengthOfPattern);
                    indexOfText += shift[elementIndex];
                    indexOfPattern = 0;
                    switchElements(shift, elementIndex);
                } else {
                    break;
                }
            }
        } while ((indexOfPattern < lengthOfPattern) && ((indexOfText + lengthOfPattern) <= lengthOfText));
        if (indexOfPattern == lengthOfPattern) {
            return indexOfText;
        } else {
            return -1;
        }
    }

    private static void switchElements(int[] shift, int index) {
        if (index < 1) return;
        int old = shift[0];
        shift[0] = shift[index];
        for (int i = 1; i < index; i++) {
            shift[i] = old;
            old = shift[i + 1];
        }
    }
}
