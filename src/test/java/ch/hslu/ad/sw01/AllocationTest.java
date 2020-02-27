package ch.hslu.ad.sw01;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AllocationTest {
    @Test
    void testToString() {
        assertThat(new Allocation(3, 3).toString()).containsSequence("Allocation[");
    }

    @Test
    void testEqualsContract() {
        EqualsVerifier.forClass(Allocation.class).verify();
    }

    @Test
    void testEqualsSame() {
        final Allocation a1 = new Allocation(12, 98);
        final Allocation a2 = a1;
        assertEquals(a1, a2);
    }

    @Test
    void testEqualsEqual() {
        final Allocation a1 = new Allocation(12, 98);
        final Allocation a2 = new Allocation(12, 98);
        assertEquals(a1, a2);
    }

    @Test
    void testEqualsNotEqual() {
        final Allocation a1 = new Allocation(12, 98);
        final Allocation a2 = new Allocation(13, 99);
        assertNotEquals(a1, a2);
    }

    @Test
    void testCompareToSmaller() {
        final Allocation a1 = new Allocation(12, 98);
        final Allocation a2 = new Allocation(13, 99);
        assertTrue(a1.compareTo(a2) > 0);
    }

    @Test
    void testCompareToBigger() {
        final Allocation a1 = new Allocation(12, 98);
        final Allocation a2 = new Allocation(13, 99);
        assertTrue(a2.compareTo(a1) < 0);
    }

    @Test
    void testCompareToSame() {
        final Allocation a1 = new Allocation(12, 99);
        final Allocation a2 = new Allocation(13, 99);
        assertEquals(0, a2.compareTo(a1));
    }
}
