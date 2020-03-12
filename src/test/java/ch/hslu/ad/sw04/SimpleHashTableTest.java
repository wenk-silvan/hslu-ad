package ch.hslu.ad.sw04;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import nl.jqno.equalsverifier.EqualsVerifier;

class SimpleHashTableTest {
    private static final int TOMBSTONE = -1;

    @Test
    void testAddInteger() {
        var expected = 3;
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        hashTable.add(expected);
        var actual = hashTable.getByIndex(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testAddIntegerTwice() {
        var expected = 3;
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        var resultAdd1 = hashTable.add(expected);
        var resultAdd2 = hashTable.add(expected);
        var actual1 = hashTable.getByIndex(expected);
        var actual2 = hashTable.getByIndex(expected + 1);

        assertTrue(resultAdd1);
        assertTrue(resultAdd2);
        assertEquals(expected, actual1);
        assertEquals(expected, actual2);
    }

    @Test
    void testAddManyInteger() {
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(5);
        hashTable.add(9);
        hashTable.add(3);

        assertNull(hashTable.getByIndex(0));
        assertEquals(1, hashTable.getByIndex(1));
        assertEquals(2, hashTable.getByIndex(2));
        assertEquals(3, hashTable.getByIndex(3));
        assertNull(hashTable.getByIndex(4));
        assertEquals(5, hashTable.getByIndex(5));
        assertNull(hashTable.getByIndex(6));
        assertNull(hashTable.getByIndex(7));
        assertNull(hashTable.getByIndex(8));
        assertEquals(9, hashTable.getByIndex(9));
    }

    @Test
    void testAddFillInteger() {
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
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
            assertEquals(i, hashTable.getByIndex(i));
        }
    }

    @Test
    void testAddFillIntegerWithDuplicates() {
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        hashTable.add(1);
        hashTable.add(5);
        hashTable.add(9);
        hashTable.add(9);
        hashTable.add(3);
        hashTable.add(8);
        hashTable.add(3);
        hashTable.add(4);
        hashTable.add(6);
        hashTable.add(1);
        var result = hashTable.add(0);

        assertFalse(result);
        assertEquals(9, hashTable.getByIndex(0));
        assertEquals(1, hashTable.getByIndex(1));
        assertEquals(1, hashTable.getByIndex(2));
        assertEquals(3, hashTable.getByIndex(3));
        assertEquals(3, hashTable.getByIndex(4));
        assertEquals(5, hashTable.getByIndex(5));
        assertEquals(4, hashTable.getByIndex(6));
        assertEquals(6, hashTable.getByIndex(7));
        assertEquals(8, hashTable.getByIndex(8));
        assertEquals(9, hashTable.getByIndex(9));
    }

    @Test
    void testAddIndexOutOfBounds() {
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        var index = 10;
        assertThrows(IndexOutOfBoundsException.class, () -> hashTable.add(index));
    }

    @Test
    void testGetInteger() {
        var expected = 3;
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        hashTable.add(expected);
        var actual = hashTable.get(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testGetIntegerWithDuplicates() {
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        hashTable.add(1);
        hashTable.add(9);
        hashTable.add(9);
        hashTable.add(0);
        var expected = hashTable.getByIndex(2);
        var actual = hashTable.get(0);
        assertEquals(expected, actual);
    }

    @Test
    void testGetNotFound() {
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        hashTable.add(1);
        hashTable.add(9);
        hashTable.add(9);
        hashTable.add(0);
        assertThrows(HashTableException.class, () -> hashTable.get(3));
    }

    @Test
    void testRemoveInteger() {
        var expected = 3;
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        hashTable.add(expected);
        var beforeRemoval = hashTable.getByIndex(expected);
        hashTable.remove(expected);
        var afterRemoval = hashTable.getByIndex(expected);

        assertEquals(expected, beforeRemoval);
        assertEquals(afterRemoval, TOMBSTONE);
    }

    @Test
    void testRemoveIntegerTwice() {
        var expected = 3;
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        hashTable.add(expected);
        var beforeRemoval = hashTable.getByIndex(expected);
        hashTable.remove(expected);
        var afterRemoval = hashTable.getByIndex(expected);
        var secondRemoval = hashTable.remove(expected);

        assertEquals(expected, beforeRemoval);
        assertEquals(afterRemoval, TOMBSTONE);
        assertFalse(secondRemoval);
    }

    @Test
    void testRemoveIntegerWithDuplicates() {
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        hashTable.add(1);
        hashTable.add(5);
        hashTable.add(9);
        hashTable.add(9);
        hashTable.remove(1);
        hashTable.add(9);

        assertEquals(9, hashTable.getByIndex(0));
        assertEquals(-1, hashTable.getByIndex(1));
        assertEquals(9, hashTable.getByIndex(2));
        assertEquals(5, hashTable.getByIndex(5));
        assertEquals(9, hashTable.getByIndex(9));
    }

    @Test
    void testRemoveIntegerFromFull() {
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        for (int i = 0; i < 10; i++) {
            hashTable.add(i);
        }

        var result = hashTable.remove(3);
        var tombstone = hashTable.getByIndex(3);

        assertTrue(result);
        assertEquals(TOMBSTONE, TOMBSTONE);
    }

    @Test
    void testRemoveIndexOutOfBounds() {
        var hashTable = new SimpleHashTable<Integer>(TOMBSTONE);
        var index = 10;
        assertThrows(IndexOutOfBoundsException.class, () -> hashTable.remove(index));
    }

    @Test
    void testEqualsContract() {
        EqualsVerifier.forClass(SimpleHashTable.class).verify();
    }
}
