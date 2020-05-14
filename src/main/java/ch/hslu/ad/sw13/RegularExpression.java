package ch.hslu.ad.sw13;
import java.util.regex.*;

public class RegularExpression {
    public static boolean isWordLanguage(final String word) {
        char previous = '_';
        int countOf1 = 0;
        int index = 0;
        for (char letter : word.toCharArray()) {
            switch (letter) {
                case '0':
                    if (countOf1 > 0 && (countOf1 % 2 == 0)) return false;
                    if (previous == letter) return false;
                    countOf1 = 0;
                    break;
                case '1':
                    if (previous == '_') return false;
                    if (index == word.length() - 1) return false;
                    countOf1++;
                    break;
                default: return false;
            }
            previous = letter;
            index++;
        }
        return true;
    }

    public static boolean isWordLanguageRegex(final String word) {
        return Pattern.matches("(01(11)*)*0", word);
    }
}
