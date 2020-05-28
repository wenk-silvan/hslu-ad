package ch.hslu.ad.sw15;

import java.awt.*;
import java.util.Objects;

public class Node {
    public Color color;
    private String name;

    public Node(final String name) {
        this.name = name;
        this.color = Color.WHITE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(color, node.color) &&
                Objects.equals(name, node.name);
    }

    @Override
    public String toString() {
        return String.format("[name=%s;color=%s]",
                name, color == Color.WHITE ? "White" : color == Color.GRAY ? "Gray" : "Black");
    }
}