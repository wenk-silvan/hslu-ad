package ch.hslu.ad.sw01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AhaDemoTest {
    @Test
    void testTaskSingle() throws InterruptedException {
        var count = 1;
        AhaDemo.reset();
        AhaDemo.task(count);
        assertEquals(AhaDemo.calledTask1, 4);
        assertEquals(AhaDemo.calledTask2, 3);
        assertEquals(AhaDemo.calledTask3, 2);
    }
    @Test
    void testTaskShort() throws InterruptedException {
        var count = 5;
        AhaDemo.reset();
        AhaDemo.task(count);
        assertEquals(AhaDemo.calledTask1, 4);
        assertEquals(AhaDemo.calledTask2, 15);
        assertEquals(AhaDemo.calledTask3, 50);
    }
    @Test
    void testTaskLong() throws InterruptedException {
        var count = 30;
        AhaDemo.reset();
        AhaDemo.task(count);
        assertEquals(AhaDemo.calledTask1, 4);
        assertEquals(AhaDemo.calledTask2, 90);
        assertEquals(AhaDemo.calledTask3, 1800);
    }
}
