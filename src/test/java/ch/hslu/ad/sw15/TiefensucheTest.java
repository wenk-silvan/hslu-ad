package ch.hslu.ad.sw15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TiefensucheTest {
    private final Node a = new Node("a");
    private final Node b = new Node("b");
    private final Node c = new Node("c");
    private final Node d = new Node("d");
    private final Node e = new Node("e");
    private final Node f = new Node("f");
    private final Node g = new Node("g");
    private final AdjazenzListe al = new AdjazenzListe();

    @BeforeEach
    void init() {
        al.addNode(a);
        al.addNode(b);
        al.addNode(c);
        al.addNode(d);
        al.addNode(e);
        al.addNode(f);
        al.addNode(g);
        al.addEdge(a, b);
        al.addEdge(a, c);
        al.addEdge(a, f);
        al.addEdge(b, c);
        al.addEdge(b, d);
        al.addEdge(d, a);
        al.addEdge(d, c);
        al.addEdge(e, c);
        al.addEdge(e, g);
        al.addEdge(f, a);
        al.addEdge(f, c);
        al.addEdge(g, d);
        al.addEdge(g, e);
    }

    @Test
    void testDfs() {
        Tiefensuche.dfs(al, g);
    }

    @Test
    void testDfsRecursive() {
        Tiefensuche.dfsRecursive(al, g);
    }
}
