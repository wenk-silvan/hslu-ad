package ch.hslu.ad.sw04;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import nl.jqno.equalsverifier.EqualsVerifier;

class AllocationHelperTest {
    private static final Logger LOGGER = LogManager.getLogger(AllocationHelperTest.class);
    private static final int SMALL = 10000;
    private static final int LARGE = 10000000;

    @Test
    void testCreateEmpty() {
        var expected = 0;
        var timeBeforeCreation = System.currentTimeMillis();
        var allocations = AllocationHelper.create(expected);
        LOGGER.info(String.format("The creation of %s items took %s milliseconds.",
                        expected,
                        System.currentTimeMillis() - timeBeforeCreation)
        );
        assertEquals(expected, allocations.length);
    }

    @Test
    void testCreateSmall() {
        var timeBeforeCreation = System.currentTimeMillis();
        var allocations = AllocationHelper.create(SMALL);
        LOGGER.info(String.format("The creation of %s items took %s milliseconds.",
                SMALL,
                System.currentTimeMillis() - timeBeforeCreation)
        );
        assertEquals(SMALL, allocations.length);
    }

    @Test
    void testCreateLarge() {
        var timeBeforeCreation = System.currentTimeMillis();
        var allocations = AllocationHelper.create(LARGE);
        LOGGER.info(String.format("The creation of %s items took %s milliseconds.",
                LARGE,
                System.currentTimeMillis() - timeBeforeCreation)
        );
        assertEquals(LARGE, allocations.length);
    }

    @Test
    void testToAllocationStackSmall() {
        var allocations = AllocationHelper.create(SMALL);
        var timeBeforeCreation = System.currentTimeMillis();
        var stack = AllocationHelper.toAllocationStack(allocations);
        LOGGER.info(String.format("Copy array with %s items to AllocationStack took %s milliseconds.",
                SMALL,
                System.currentTimeMillis() - timeBeforeCreation)
        );
        assertEquals(SMALL, stack.size());
    }

    @Test
    void testToJavaStackSmall() {
        var allocations = AllocationHelper.create(SMALL);
        var timeBeforeCreation = System.currentTimeMillis();
        var stack = AllocationHelper.toJavaStack(allocations);
        LOGGER.info(String.format("Copy array with %s items to Java Stack took %s milliseconds.",
                SMALL,
                System.currentTimeMillis() - timeBeforeCreation)
        );
        assertEquals(SMALL, stack.size());
    }

    @Test
    void testToJavaStackLarge() {
        var allocations = AllocationHelper.create(LARGE);
        var timeBeforeCreation = System.currentTimeMillis();
        var stack = AllocationHelper.toJavaStack(allocations);
        LOGGER.info(String.format("Copy array with %s items to Java Stack took %s milliseconds.",
                LARGE,
                System.currentTimeMillis() - timeBeforeCreation)
        );

        timeBeforeCreation = System.currentTimeMillis();
        stack = AllocationHelper.toJavaStack(allocations);
        LOGGER.info(String.format("Copy array with %s items to Java Stack took %s milliseconds.",
                LARGE,
                System.currentTimeMillis() - timeBeforeCreation)
        );


        timeBeforeCreation = System.currentTimeMillis();
        stack = AllocationHelper.toJavaStack(allocations);
        LOGGER.info(String.format("Copy array with %s items to Java Stack took %s milliseconds.",
                LARGE,
                System.currentTimeMillis() - timeBeforeCreation)
        );

        timeBeforeCreation = System.currentTimeMillis();
        stack = AllocationHelper.toJavaStack(allocations);
        LOGGER.info(String.format("Copy array with %s items to Java Stack took %s milliseconds.",
                LARGE,
                System.currentTimeMillis() - timeBeforeCreation)
        );

        timeBeforeCreation = System.currentTimeMillis();
        stack = AllocationHelper.toJavaStack(allocations);
        LOGGER.info(String.format("Copy array with %s items to Java Stack took %s milliseconds.",
                LARGE,
                System.currentTimeMillis() - timeBeforeCreation)
        );

        assertEquals(LARGE, stack.size());
    }

    @Test
    void testToDequeSmall() {
        var allocations = AllocationHelper.create(SMALL);
        var timeBeforeCreation = System.currentTimeMillis();
        var deque = AllocationHelper.toDeque(allocations);
        LOGGER.info(String.format("Copy array with %s items to ArrayDeque took %s milliseconds.",
                SMALL,
                System.currentTimeMillis() - timeBeforeCreation)
        );
        assertEquals(SMALL, deque.size());
    }

    @Test
    void testToDequeLarge() {
        var allocations = AllocationHelper.create(LARGE);
        var timeBeforeCreation = System.currentTimeMillis();
        var deque = AllocationHelper.toDeque(allocations);
        LOGGER.info(String.format("Copy array with %s items to ArrayDeque took %s milliseconds.",
                LARGE,
                System.currentTimeMillis() - timeBeforeCreation)
        );
        assertEquals(LARGE, deque.size());
    }
}
