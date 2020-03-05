package ch.hslu.ad.sw03;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IntTreeTest {
    private IntNode root;

    @BeforeEach
    void initialize() {
        Node n1 = new IntNode(new IntLeaf(1), null, 2);
        Node n2 = new IntNode(null, new IntLeaf(5), 4);
        Node n3 = new IntNode(new IntLeaf(7), new IntLeaf(9), 8);
        Node n4 = new IntNode(n1, n2, 3);
        Node n5 = new IntNode(n3, null, 10);
        this.root = new IntNode(n4, n5, 6);
    }

    @Test
    void testSearchNode() {
        var tree = new IntTree(this.root);
        var expected = 8;
        var node = tree.search(expected);
        assertThat(node).isNotNull();
        assertEquals(expected, node.get());
    }

    @Test
    void testSearchLeaf() {
        var tree = new IntTree(this.root);
        var expected = 7;
        var node = tree.search(expected);
        assertThat(node).isNotNull();
        assertEquals(expected, node.get());
    }

    @Test
    void testSearchRoot() {
        var tree = new IntTree(this.root);
        var expected = 6;
        var node = tree.search(expected);
        assertThat(node).isNotNull();
        assertEquals(expected, node.get());
    }

    @Test
    void testSearchNodeWithoutChildrenThrows() {
        Node n1 = new IntNode(null, null, 2);
        Node n2 = new IntNode(null, new IntLeaf(5), 4);
        Node n3 = new IntNode(new IntLeaf(7), new IntLeaf(9), 8);
        Node n4 = new IntNode(n1, n2, 3);
        Node n5 = new IntNode(n3, null, 10);
        var tree = new IntTree(new IntNode(n4, n5, 6));
        var expected = 1;
        assertThrows(TreeException.class, () -> tree.search(expected));
    }

    @Test
    void testAddLeaf() {
        var tree = new IntTree(this.root);
        var expected = new IntLeaf(11);
        tree.add(expected);
        var actual = tree.search(11);
        assertEquals(expected, actual);
    }

    @Test
    void testAddNode() {
        var tree = new IntTree(this.root);
        var expected = new IntNode(new IntLeaf(12), null, 11);
        tree.add(expected);
        var actual = tree.search(11);
        assertEquals(expected, actual);
    }

    @Test
    void testTraverseInorder() {
        var tree = new IntTree();
        tree.traverseInorder(this.root);
    }

    @Test
    void testTraversePostorder() {
        var tree = new IntTree();
        tree.traversePostorder(this.root);
    }

    @Test
    void testAddNodeWithoutChildrenThrows() {
        Node n1 = new IntNode(null, null, 2);
        Node n2 = new IntNode(null, new IntLeaf(5), 4);
        Node n3 = new IntNode(new IntLeaf(7), new IntLeaf(9), 8);
        Node n4 = new IntNode(n1, n2, 3);
        Node n5 = new IntNode(n3, null, 10);
        var tree = new IntTree(new IntNode(n4, n5, 6));
        var expected = new IntLeaf(7);
        assertThrows(TreeException.class, () -> tree.add(expected));
    }
}
