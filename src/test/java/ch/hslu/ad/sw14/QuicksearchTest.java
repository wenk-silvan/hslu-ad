package ch.hslu.ad.sw14;

import ch.hslu.ad.helper.Timer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuicksearchTest {
    private static final Logger LOG = LogManager.getLogger(QuicksearchTest.class);
    @Test
    void quickSortTestSimple() {
        String pattern = "hello";
        String text = "healleodlldjaehlaflekjhellojdk";
        int expected = 22;
        int actual = Quicksearch.quickSearch(text, pattern);
        assertEquals(expected, actual);
    }

    @Test
    void quickSortTestComplex() {
        String pattern = "hello";
        String text = "healleodlldjaehlaflekjheldlojdkkjfehfasjeflkefjasefhehalahehllhelhehellojkl";
        int expected = 67;
        int actual = Quicksearch.quickSearch(text, pattern);
        assertEquals(expected, actual);
    }

    @Test
    void quickSortTestNotFound() {
        String pattern = "hello";
        String text = "healleodlldjaehdlaflekjheldlojdkkjfehfasjeflkefjasefhehalahehllhelhehllojkl";
        int expected = -1;
        int actual = Quicksearch.quickSearch(text, pattern);
        assertEquals(expected, actual);
    }

    @Test
    void quickSortTestGutenberg() {
        String pattern = "Tröstet Euch, gnädige Frau!";
        int expected = 171283;
        try {
            String text = Files.readString(Path.of("src/test/java/ch/hslu/ad/sw14/gutenberg.txt"));
            var actual = (new Timer<Integer>()).stopWatchNano(LOG, () -> Quicksearch.quickSearch(text, pattern));
            assertEquals(expected, actual);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Test
    void optimalMismatchTestSimple() {
        String pattern = "hello";
        String text = "healleodlldjaehlaflekjhellojdk";
        int expected = 22;
        int actual = Quicksearch.optimalMismatch(text, pattern);
        assertEquals(expected, actual);
    }

    @Test
    void optimalMismatchTestComplex() {
        String pattern = "hello";
        String text = "healleodlldjaehlaflekjheldlojdkkjfehfasjeflkefjasefhehalahehllhelhehellojkl";
        int expected = 67;
        int actual = Quicksearch.optimalMismatch(text, pattern);
        assertEquals(expected, actual);
    }

    @Test
    void optimalMismatchTestNotFound() {
        String pattern = "hello";
        String text = "healleodlldjaehdlaflekjheldlojdkkjfehfasjeflkefjasefhehalahehllhelhehllojkl";
        int expected = -1;
        int actual = Quicksearch.optimalMismatch(text, pattern);
        assertEquals(expected, actual);
    }

    @Test
    void optimalMismatchTestGutenberg() {
        String pattern = "Tröstet Euch, gnädige Frau!";
        int expected = 171283;
        try {
            String text = Files.readString(Path.of("src/test/java/ch/hslu/ad/sw14/gutenberg.txt"));
            var actual = (new Timer<Integer>()).stopWatchNano(LOG, () -> Quicksearch.optimalMismatch(text, pattern));
            assertEquals(expected, actual);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
