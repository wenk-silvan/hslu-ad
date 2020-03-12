package ch.hslu.ad.sw04;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import nl.jqno.equalsverifier.EqualsVerifier;

class SimpleHashTableTest {

    @Test
    void testAddInteger() {
        var expected = 3;
        var hashTable = new SimpleHashTable<Integer>();
        hashTable.add(expected);
        var actual = hashTable.get(expected);
        assertEquals(expected, actual);
    }

    @Test
    void testAddIntegerTwice() {
        var expected = 3;
        var hashTable = new SimpleHashTable<Integer>();
        var resultAdd1 = hashTable.add(expected);
        var resultAdd2 = hashTable.add(expected);
        var actual = hashTable.get(expected);

        assertTrue(resultAdd1);
        assertFalse(resultAdd2);
        assertEquals(expected, actual);
    }

    @Test
    void testAddManyInteger() {
        var hashTable = new SimpleHashTable<Integer>();
        hashTable.add(1);
        hashTable.add(2);
        hashTable.add(5);
        hashTable.add(9);
        hashTable.add(3);

        assertNull(hashTable.get(0));
        assertEquals(1, hashTable.get(1));
        assertEquals(2, hashTable.get(2));
        assertEquals(3, hashTable.get(3));
        assertNull(hashTable.get(4));
        assertEquals(5, hashTable.get(5));
        assertNull(hashTable.get(6));
        assertNull(hashTable.get(7));
        assertNull(hashTable.get(8));
        assertEquals(9, hashTable.get(9));
    }

    @Test
    void testAddIndexOutOfBounds() {
        var hashTable = new SimpleHashTable<Integer>();
        var index = 10;
        assertThrows(IndexOutOfBoundsException.class, () -> hashTable.add(index));
    }

    @Test
    void testRemoveInteger() {
        var expected = 3;
        var hashTable = new SimpleHashTable<Integer>();
        hashTable.add(expected);
        var beforeRemoval = hashTable.get(expected);
        hashTable.remove(expected);
        var afterRemoval = hashTable.get(expected);

        assertEquals(expected, beforeRemoval);
        assertNull(afterRemoval);
    }

    @Test
    void testRemoveIntegerTwice() {
        var expected = 3;
        var hashTable = new SimpleHashTable<Integer>();
        hashTable.add(expected);
        var beforeRemoval = hashTable.get(expected);
        hashTable.remove(expected);
        var afterRemoval = hashTable.get(expected);
        var secondRemoval = hashTable.remove(expected);

        assertEquals(expected, beforeRemoval);
        assertNull(afterRemoval);
        assertFalse(secondRemoval);
    }

    @Test
    void testRemoveIndexOutOfBounds() {
        var hashTable = new SimpleHashTable<Integer>();
        var index = 10;
        assertThrows(IndexOutOfBoundsException.class, () -> hashTable.remove(index));
    }
}
