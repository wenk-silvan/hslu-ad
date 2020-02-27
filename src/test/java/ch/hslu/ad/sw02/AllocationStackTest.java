package ch.hslu.ad.sw02;

import ch.hslu.ad.sw01.Allocation;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AllocationStackTest {
    @Test
    void testStackEmpty() {
        var stack = new AllocationStack(0);
        assertTrue(stack.isEmpty());
    }

    @Test
    void testStackNotEmpty() {
        var stack = new AllocationStack(2);
        var a = new Allocation(2, 10);
        stack.push(a);
        assertFalse(stack.isEmpty());
    }

    @Test
    void testStackIsFull() {
        var stack = new AllocationStack(1);
        var a = new Allocation(2, 10);
        stack.push(a);
        assertEquals(1, stack.size());
    }

    @Test
    void testPushAndPop() {
        var stack = new AllocationStack(3);
        var a = new Allocation(2, 10);
        var result = stack.push(a);
        var actual = stack.pop();
        assertEquals(1, result);
        assertEquals(actual, a);
    }

    @Test
    void testPushTooSmallStack() {
        var stack = new AllocationStack(2);
        var a = new Allocation(2, 10);
        var result1 = stack.push(a);
        var result2 = stack.push(a);
        var result3 = stack.push(a);
        var result4 = stack.push(a);
        assertEquals(1, result1);
        assertEquals(1, result2);
        assertEquals(-1, result3);
        assertEquals(-1, result4);
    }

    @Test
    void testPopEmptyStack() {
        var stack = new AllocationStack(2);
        var a = stack.pop();
        assertThat(a).isNull();
    }
}
