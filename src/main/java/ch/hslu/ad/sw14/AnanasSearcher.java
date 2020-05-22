package ch.hslu.ad.sw14;

public class AnanasSearcher {
    public static int stateSearch(final String text) {
        int i = 0;
        String state = "0";
        final int notFound = -1;
        do {
            switch (state) {
                case "0":
                    if (text.charAt(i) == 'A') state = "A";
                    break;
                case "A":
                    if (text.charAt(i) == 'N') state = "AN";
                    else if (text.charAt(i) == 'A') state = "A";
                    else state = "0";
                    break;
                case "AN":
                    if (text.charAt(i) == 'A') state = "ANA";
                    else state = "0";
                    break;
                case "ANA":
                    if (text.charAt(i) == 'N') state = "ANAN";
                    else if (text.charAt(i) == 'A') state = "A";
                    else state = "0";
                    break;
                case "ANAN":
                    if (text.charAt(i) == 'A') state = "ANANA";
                    else if (text.charAt(i) == 'N') state = "ANA";
                    else state = "0";
                    break;
                case "ANANA":
                    if (text.charAt(i) == 'S') state = "ANANAS";
                    else if (text.charAt(i) == 'A') state = "A";
                    else if (text.charAt(i) == 'N') state = "ANAN";
                    else state = "0";
                    break;
            }
            i++;
        } while ((!state.equals("ANANAS")) && i < text.length());
        if (state.equals("ANANAS")) {
            return (i - "ANANAS".length());
        }
        return notFound;
    }
}
