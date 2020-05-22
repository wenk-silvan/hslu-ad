package ch.hslu.ad.sw14;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class AnanasSearcherTest {
    @Test
    void testStateSearchEasy() {
        final String text = "ANANANAS";
        final int expected = 2;
        final int actual = AnanasSearcher.stateSearch(text);
        assertEquals(actual, expected);
    }

    @Test
    void testStateSearchComplex() {
        final String text = "ANSANANSSANANAANANANASSNNA";
        final int expected = 16;
        final int actual = AnanasSearcher.stateSearch(text);
        assertEquals(actual, expected);
    }

    @Test
    void testStateSearchFailed() {
        final String text = "ANSANANSSANANAANASNANASSNNA";
        final int expected = -1;
        final int actual = AnanasSearcher.stateSearch(text);
        assertEquals(actual, expected);
    }
}
