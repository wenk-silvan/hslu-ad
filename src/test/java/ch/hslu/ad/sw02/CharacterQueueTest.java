package ch.hslu.ad.sw02;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CharacterQueueTest {
    @Test
    void testPoll() {
        char expected = 'C';
        var queue = new CharacterQueue(expected);
        var actual = queue.poll();
        assertEquals(expected, actual);
    }

    @Test
    void testPollEmptyQueue() {
        var queue = new CharacterQueue();
        var actual = queue.poll();
        assertNull(actual);
    }

    @Test
    void testOffer() {
        char expected = 'b';
        var queue = new CharacterQueue('a');
        queue.offer(expected);
        var actual = queue.poll();
        assertEquals(expected, actual);
    }

    @Test
    void testOfferEmptyQueue() {
        char expected = 'C';
        var queue = new CharacterQueue();
        queue.offer(expected);
        var actual = queue.poll();
        assertEquals(expected, actual);
    }

    @Test
    void testOfferAndPollFullQueue() {
        char c1 = 'a';
        char c2 = 'b';
        char c3 = 'c';
        char c4 = 'd';
        char c5 = 'e';
        char c6 = 'f';
        char c7 = 'g';
        char c8 = 'h';
        char c9 = 'i';
        var queue = new CharacterQueue(c1);
        queue.offer(c2);
        queue.offer(c3);
        queue.offer(c4);
        queue.offer(c5);
        queue.offer(c6);
        queue.offer(c7);
        queue.offer(c8);
        queue.offer(c9);
        assertEquals(c9, queue.poll());
        assertEquals(c8, queue.poll());
        assertEquals(c7, queue.poll());
        assertEquals(c6, queue.poll());
        assertEquals(c5, queue.poll());
        assertEquals(c4, queue.poll());
        assertEquals(c3, queue.poll());
        assertEquals(c2, queue.poll());
        assertNull(queue.poll());
    }

    @Test
    void testSize() {
        char c1 = 'a';
        char c2 = 'b';
        char c3 = 'c';
        char c4 = 'd';
        var queue = new CharacterQueue(c1);
        queue.offer(c2);
        queue.offer(c3);
        queue.offer(c4);
        var actual = queue.size();
        assertEquals(4, actual);
    }

    @Test
    void testSizeFullQueue() {
        var queue = new CharacterQueue('a');
        queue.offer('b');
        queue.offer('c');
        queue.offer('d');
        queue.offer('e');
        queue.offer('f');
        queue.offer('g');
        queue.offer('h');
        queue.offer('i');
        queue.offer('j');
        var actual = queue.size();
        assertEquals(8, actual);
    }

    @Test
    void testSizeComplex() {
        var queue = new CharacterQueue('a');
        Stream.of(new Character[]{'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k'}).forEach(queue::offer);
        IntStream.range(0, 4).forEach(i -> queue.poll());
        var actual = queue.size();
        assertEquals(4, actual);
    }

    @Test
    void testSizeEmptyQueue() {
        var queue = new CharacterQueue();
        var actual = queue.size();
        assertEquals(0, actual);
    }
}
