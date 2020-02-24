package ch.hslu.ad.sw01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AhaDemoTest {
    @Test
    void testTaskSingle() {
        var count = 1;
        AhaDemo.task(count);
        assertEquals(AhaDemo.calledTask1, 4);
        assertEquals(AhaDemo.calledTask2, 3);
        assertEquals(AhaDemo.calledTask3, 2);
    }
    @Test
    void testTaskShort() {
        var count = 5;
        AhaDemo.task(count);
        assertEquals(AhaDemo.calledTask1, 4);
        assertEquals(AhaDemo.calledTask2, 15);
        assertEquals(AhaDemo.calledTask3, 50);
    }
    @Test
    void testTaskLong() {
        var count = 30;
        AhaDemo.task(count);
        assertEquals(AhaDemo.calledTask1, 4);
        assertEquals(AhaDemo.calledTask2, 90);
        assertEquals(AhaDemo.calledTask3, 1800);
    }
}
