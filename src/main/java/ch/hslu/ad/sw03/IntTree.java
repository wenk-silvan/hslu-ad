package ch.hslu.ad.sw03;

import ch.hslu.demo.DemoApp;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class IntTree implements Tree {
    private IntNode root;
    private static final Logger LOGGER = LogManager.getLogger(DemoApp.class);

    IntTree() {
        this.root = new IntNode(null, null, null);
    }

    IntTree(IntNode node) {
        this.root = node;
    }

    /**
     * Preorder search for node with specified value.
     * @param value Integer value.
     * @return the found node. Null, if none was found.
     */
    @Override
    public Node search(Object value) {
        try {
            var intValue = (int) value;
            return this.findNode(this.root, intValue);
        } catch (TreeException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void add(Node node) {
        this.addNode(this.root, node);
    }

    @Override
    public IntNode getRoot() {
        return null;
    }

    @Override
    public void remove() {

    }

    void traverseInorder(Node node) {
        if (node instanceof IntLeaf) {
            LOGGER.info(String.format("Traverse: %s", node.toString()));
            return;
        }
        var inner = (IntNode) node;
        if (inner.hasLeft()) {
            this.traverseInorder(inner.left());
        }
        LOGGER.info(String.format("Traverse: %s", node.toString()));
        if (inner.hasRight()) {
            this.traverseInorder(inner.right());
        }
    }

    void traversePostorder(Node node) {
        if (node instanceof IntLeaf) {
            LOGGER.info(String.format("Traverse: %s", node.toString()));
            return;
        }
        var inner = (IntNode) node;
        if (inner.hasLeft()) {
            this.traversePostorder(inner.left());
        }
        if (inner.hasRight()) {
            this.traversePostorder(inner.right());
        }
        LOGGER.info(String.format("Traverse: %s", node.toString()));
    }

    private Node findNode(Node node, int value) {
        if ((int) node.get() == value) {
            LOGGER.info(String.format("FOUND: %s", node.toString()));
            return node;
        }
        LOGGER.info(String.format("SEARCH: %s", node.toString()));

        if (node instanceof IntLeaf) {
            return null;
        }

        var inner = (IntNode) node;

        if (!inner.hasLeft() && !inner.hasRight()) {
            throw new TreeException(String.format("The inner %s has no children.", node.toString()));
        }

        Node left = inner.hasLeft() ? this.findNode(inner.left(), value) : null;
        if (left != null) {
            return left;
        }
        return inner.hasRight() ? this.findNode(inner.right(), value) : null;
    }

    private void addNode(Node node, Node newNode) {
        var value = (int) node.get();
        var newValue = (int) newNode.get();
        LOGGER.info(String.format("FIND PLACE: Current: %s New: %s", node.toString(), newNode.toString()));
        if (value == newValue) {
            throw new TreeException(String.format("Cannot add node, %s already exists.", node.toString()));
        }

        if (node instanceof IntLeaf) {
            node = newValue < value ? new IntNode(newNode, null, value) : new IntNode(null, newNode, value);
        }

        var intNode = (IntNode) node;
        if (newValue < value) {
            if (!intNode.hasLeft()) {
                LOGGER.info(String.format("ADD LEFT: Current: %s New: %s", node.toString(), newNode.toString()));
                intNode.setLeft(newNode);
                return;
            }
            this.addNode(intNode.left(), newNode);
        }
        if (!intNode.hasRight()) {
            LOGGER.info(String.format("ADD RIGHT: Current: %s New: %s", node.toString(), newNode.toString()));
            intNode.setRight(newNode);
            return;
        }
        this.addNode(intNode.right(), newNode);
    }
}
