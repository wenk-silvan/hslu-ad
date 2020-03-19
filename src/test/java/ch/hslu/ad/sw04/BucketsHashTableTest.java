package ch.hslu.ad.sw04;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BucketsHashTableTest {

    @Test
    void testAddInteger() {
        var expected = 3;
        var hashTable = new BucketsHashTable<Integer>(10);
        hashTable.add(expected);
        var actual = hashTable.getByIndex(expected);

        assertNotNull(actual);
        assertEquals(expected, actual.get());
    }

    @Test
    void testAddDuplicateThrows() {
        var expected = 3;
        var hashTable = new BucketsHashTable<Integer>(10);
        var result = hashTable.add(expected);
        assertTrue(result);
        assertThrows(HashTableException.class, () -> hashTable.add(expected));
    }

    @Test
    void testAddManyInteger() {
        var hashTable = new BucketsHashTable<Integer>(10);
        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(5);
        hashTable.add(9);
        hashTable.add(3);

        assertNull(hashTable.getByIndex(0));
        assertNull(hashTable.getByIndex(4));
        assertNull(hashTable.getByIndex(6));
        assertNull(hashTable.getByIndex(7));
        assertNull(hashTable.getByIndex(8));

        assertEquals(1, hashTable.getByIndex(1).get());
        assertEquals(2, hashTable.getByIndex(2).get());
        assertEquals(3, hashTable.getByIndex(3).get());
        assertEquals(5, hashTable.getByIndex(5).get());
        assertEquals(9, hashTable.getByIndex(9).get());
    }

    @Test
    void testAddFillInteger() {
        var hashTable = new BucketsHashTable<Integer>(10);
        hashTable.add(1);
        hashTable.add(5);
        hashTable.add(9);
        hashTable.add(2);
        hashTable.add(3);
        hashTable.add(8);
        hashTable.add(7);
        hashTable.add(4);
        hashTable.add(6);
        hashTable.add(0);

        for (int i = 0; i < 10; i++) {
            assertEquals(i, hashTable.getByIndex(i).get());
        }
    }

    @Test
    void testAddFillIntegerWithCollision() {
        var hashTable = new BucketsHashTable<Integer>(10);
        hashTable.add(1);
        hashTable.add(5);
        hashTable.add(9);
        hashTable.add(19);
        hashTable.add(3);
        hashTable.add(8);
        hashTable.add(13);
        hashTable.add(4);
        hashTable.add(6);
        hashTable.add(11);
        hashTable.add(0);

        assertEquals(1, hashTable.getByIndex(1).get());
        assertTrue(hashTable.getByIndex(1).hasChild());
        assertEquals(11, hashTable.getByIndex(1).child().get());
        assertEquals(3, hashTable.getByIndex(3).get());
        assertTrue(hashTable.getByIndex(3).hasChild());
        assertEquals(13, hashTable.getByIndex(3).child().get());
        assertEquals(5, hashTable.getByIndex(5).get());
        assertEquals(4, hashTable.getByIndex(4).get());
        assertEquals(6, hashTable.getByIndex(6).get());
        assertEquals(8, hashTable.getByIndex(8).get());
        assertEquals(9, hashTable.getByIndex(9).get());
        assertTrue(hashTable.getByIndex(9).hasChild());
        assertEquals(19, hashTable.getByIndex(9).child().get());
    }

    @Test
    void testGetInteger() {
        var expected = 3;
        var hashTable = new BucketsHashTable<Integer>(10);
        hashTable.add(expected);
        var actual = hashTable.get(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testGetIntegerWithCollision() {
        var hashTable = new BucketsHashTable<Integer>(10);
        var expected = 19;
        hashTable.add(1);
        hashTable.add(9);
        hashTable.add(expected);
        hashTable.add(0);
        var actual = hashTable.get(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testGetNotFoundThrows() {
        var hashTable = new BucketsHashTable<Integer>(10);
        hashTable.add(1);
        hashTable.add(9);
        hashTable.add(0);
        assertThrows(HashTableException.class, () -> hashTable.get(3));
    }

    @Test
    void testRemoveInteger() {
        var expected = 3;
        var hashTable = new BucketsHashTable<Integer>(10);
        hashTable.add(expected);
        var beforeRemoval = hashTable.getByIndex(expected);
        hashTable.remove(expected);
        var afterRemoval = hashTable.getByIndex(expected);

        assertEquals(expected, beforeRemoval.get());
        assertNull(afterRemoval);
    }

    @Test
    void testRemoveIntegerTwiceThrows() {
        var expected = 3;
        var hashTable = new BucketsHashTable<Integer>(10);
        hashTable.add(expected);
        hashTable.remove(expected);
        assertThrows(HashTableException.class, () -> hashTable.remove(expected));
    }

    @Test
    void testRemoveIntegerTwiceWithCollision() {
        var hashTable = new BucketsHashTable<Integer>(10);
        hashTable.add(3);
        hashTable.add(13);

        hashTable.remove(3);
        var node = hashTable.getByIndex(3);
        assertNotNull(node);
        assertEquals(13, node.get());

        hashTable.remove(13);
        node = hashTable.getByIndex(3);
        assertNull(node);

    }

    @Test
    void testRemoveNotFoundThrows() {
        var hashTable = new BucketsHashTable<Integer>(10);
        for (int i = 0; i < 9; i++) {
            hashTable.add(i);
        }
        assertThrows(HashTableException.class, () -> hashTable.remove(9));
    }
}
