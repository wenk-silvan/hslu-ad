package ch.hslu.ad.sw01;

import ch.hslu.demo.DemoApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FibonacciDemoTest {
    private static final Logger LOGGER = LogManager.getLogger(DemoApp.class);

    @Test
    void testFiboRec1Small() {
        int index = 3;
        long value = FibonacciDemo.fiboRec1(index);
        assertEquals(2, value);
    }

    @Test
    void testFiboIterSmall() {
        int index = 3;
        long value = FibonacciDemo.fiboIter(index);
        assertEquals(2, value);
    }

    @Test
    void testFiboRec1Medium() {
        int index = 10;
        long value = FibonacciDemo.fiboRec1(index);
        assertEquals(55, value);
    }

    @Test
    void testFiboIterMedium() {
        int index = 10;
        long value = FibonacciDemo.fiboIter(index);
        assertEquals(55, value);
    }

    @Test
    void testFiboRec1Big() {
        int index = 40;
        var durationInMilliseconds = System.currentTimeMillis();
        long value = FibonacciDemo.fiboRec1(index);
        assertEquals(102334155, value);
        LOGGER.info("The program took {} milliseconds to execute.",
                System.currentTimeMillis() - durationInMilliseconds);
    }

    @Test
    void testFiboIterBig() {
        int index = 40;
        var durationInMilliseconds = System.currentTimeMillis();
        long value = FibonacciDemo.fiboIter(index);
        assertEquals(102334155, value);
        LOGGER.info("The program took {} milliseconds to execute.",
                System.currentTimeMillis() - durationInMilliseconds);
    }
}
