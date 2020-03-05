package ch.hslu.ad.sw03;

public class IntTree implements Tree {
    private IntNode root;

    public IntTree() {
        IntNode<Integer> n1 = new IntNode<>(new IntLeaf<>(1), null, 2);
        IntNode<Integer> n2 = new IntNode<>(null, new IntLeaf<>(5), 4);
        IntNode<Integer> n3 = new IntNode<>(new IntLeaf<>(7), new IntLeaf<>(9), 8);
        IntNode<Integer> n4 = new IntNode<>(n1, n2, 3);
        IntNode<Integer> n5 = new IntNode<>(n3, null, 10);
        this.root = new IntNode(n4, n5, 6);
    }

    @Override
    public Node search(Object value) {
        try {
            var intValue = (int) value;
            return this.findValue(this.root, intValue);
        } catch (TreeException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(Node node) {

    }

    @Override
    public IntNode getRoot() {
        return null;
    }

    @Override
    public void remove() {

    }

    private Node findValue(Node node, int value) throws TreeException {
        if ((int) node.get() == value) {
            return node;
        }

        if (node instanceof IntLeaf) {
            return null;
        }

        var inner = (IntNode) node;

        if (!inner.hasLeft() && !inner.hasRight()) {
            throw new TreeException(String.format("The inner node (value: %s) has no children.", node.get()));
        }

        Node left = inner.hasLeft() ? this.findValue(inner.left(), value) : null;
        Node right = inner.hasRight() ? this.findValue(inner.right(), value) : null;
        return left != null ? left : right;
    }
}
