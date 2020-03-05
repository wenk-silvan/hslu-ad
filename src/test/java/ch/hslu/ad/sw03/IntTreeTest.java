package ch.hslu.ad.sw03;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IntTreeTest {
    @Test
    void testSearchNode() {
        var tree = new IntTree();
        var expected = 8;
        var node = tree.search(expected);
        assertThat(node).isNotNull();
        assertEquals(expected, node.get());
    }

    @Test
    void testSearchLeaf() {
        var tree = new IntTree();
        var expected = 7;
        var node = tree.search(expected);
        assertThat(node).isNotNull();
        assertEquals(expected, node.get());
    }

    @Test
    void testSearchRoot() {
        var tree = new IntTree();
        var expected = 6;
        var node = tree.search(expected);
        assertThat(node).isNotNull();
        assertEquals(expected, node.get());
    }
}
