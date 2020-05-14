package ch.hslu.ad.sw13;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegularExpressionTest {
    @Test
    void testIsWordLanguage() {
        final String word = "011101010111110";
        var actual = RegularExpression.isWordLanguage(word);
        assertTrue(actual);
    }

    @Test
    void testIsWordLanguageRegex() {
        final String word = "011101010111110";
        var actual = RegularExpression.isWordLanguageRegex(word);
        assertTrue(actual);
    }

    @Test
    void testIsWordLanguageSimple() {
        final String word = "010";
        var actual = RegularExpression.isWordLanguage(word);
        assertTrue(actual);
    }

    @Test
    void testIsWordLanguageRegexSimple() {
        final String word = "010";
        var actual = RegularExpression.isWordLanguageRegex(word);
        assertTrue(actual);
    }

    @Test
    void testIsWordLanguageComplex() {
        final String word = "01111101011101011111110111111111010";
        var actual = RegularExpression.isWordLanguage(word);
        assertTrue(actual);
    }

    @Test
    void testIsWordLanguageRegexComplex() {
        final String word = "01111101011101011111110111111111010";
        var actual = RegularExpression.isWordLanguageRegex(word);
        assertTrue(actual);
    }

    @Test
    void testIsWordLanguageFalse() {
        assertFalse(RegularExpression.isWordLanguage("1"));
        assertFalse(RegularExpression.isWordLanguage("10"));
        assertFalse(RegularExpression.isWordLanguage("01"));
        assertFalse(RegularExpression.isWordLanguage("0110"));
        assertFalse(RegularExpression.isWordLanguage("01100"));
        assertFalse(RegularExpression.isWordLanguage("01110101"));
        assertFalse(RegularExpression.isWordLanguage("0111010110"));
        assertFalse(RegularExpression.isWordLanguage("0111110101110101111110111111111010"));
    }

    @Test
    void testIsWordLanguageRegexFalse() {
        assertFalse(RegularExpression.isWordLanguageRegex("1"));
        assertFalse(RegularExpression.isWordLanguageRegex("10"));
        assertFalse(RegularExpression.isWordLanguageRegex("01"));
        assertFalse(RegularExpression.isWordLanguageRegex("0110"));
        assertFalse(RegularExpression.isWordLanguageRegex("01100"));
        assertFalse(RegularExpression.isWordLanguageRegex("01110101"));
        assertFalse(RegularExpression.isWordLanguageRegex("0111010110"));
        assertFalse(RegularExpression.isWordLanguageRegex("0111110101110101111110111111111010"));
    }
}
