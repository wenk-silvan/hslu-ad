package ch.hslu.ad.sw02;

import ch.hslu.ad.sw01.Allocation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AllocationListTest {
    private AllocationList list;
    private AllocationNode node;

    @BeforeEach
    void initialize() {
        var a1 = new Allocation(2, 10);
        var a2 = new Allocation(2, 11);
        var a3 = new Allocation(3, 12);
        var a4 = new Allocation(3, 13);
        var a5 = new Allocation(4, 14);
        this.list = new AllocationList();
        list.add(a1);
        list.add(a2);
        list.add(a3);
        list.add(a4);
        list.add(a5);
    }

    @Test
    void testCount() {
        var length = this.list.count();
        assertEquals(5, length);
    }

    @Test
    void testCountWrong() {
        var length = this.list.count();
        assertThat(length).isNotEqualTo(4);
    }

    @Test
    void testAddSingle() {
        var a = new Allocation(5, 15);
        this.list.add(a);
        assertEquals(6, this.list.count());
        assertEquals(15, this.list.getHead().get().getStartAddress());
    }

    @Test
    void testAddMultiple() {
        var a1 = new Allocation(5, 15);
        var a2 = new Allocation(5, 16);
        var a3 = new Allocation(5, 17);
        var allocations = Arrays.asList(a1, a2, a3);
        this.list.add(allocations);
        assertEquals(8, this.list.count());
        assertEquals(17, this.list.getHead().get().getStartAddress());
    }

    @Test
    void testRemoveHead() {
        var length = this.list.remove(0);
        assertEquals(4, length);
        assertEquals(13, this.list.getHead().get().getStartAddress());
    }

    @Test
    void testRemoveTail() {
        var length = this.list.remove(4);
        assertEquals(4, length);
    }

    @Test
    void testRemoveAny() {
        var length = this.list.remove(2);
        assertEquals(4, length);
    }

    @Test
    void testRemoveFromEmpty() {
        var emptyList = new AllocationList();
        var length = emptyList.remove(2);
        assertEquals(0, length);
    }

    @Test
    void testRemoveIndexOutOfBounds() {
        var length = this.list.remove(8);
        assertEquals(5, length);
    }
}
