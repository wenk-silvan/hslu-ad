package ch.hslu.ad.sw15;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AdjazenzListeTest {
    private static final Logger LOG = LogManager.getLogger(AdjazenzListeTest.class);
    private Node aarau = new Node("Aarau");
    private Node brugg = new Node("Brugg");
    private Node dietikon = new Node("Dietikon");
    private Node lenzburg = new Node("Lenzburg");
    private Node wohlen = new Node("Wohlen");
    private Node rotkreuz = new Node("Rotkreuz");
    private Node zug = new Node("Zug");
    private Node zurich = new Node("Zurich");
    private AdjazenzListe al = new AdjazenzListe();

    @BeforeEach
    void initTrainNetwork() {
        al.addNode(aarau);
        al.addNode(brugg);
        al.addNode(dietikon);
        al.addNode(lenzburg);
        al.addNode(wohlen);
        al.addNode(rotkreuz);
        al.addNode(zug);
        al.addNode(zurich);
        al.addEdge(aarau, brugg);
        al.addEdge(aarau, lenzburg);
        al.addEdge(brugg, aarau);
        al.addEdge(brugg, dietikon);
        al.addEdge(brugg, lenzburg);
        al.addEdge(dietikon, brugg);
        al.addEdge(dietikon, lenzburg);
        al.addEdge(dietikon, wohlen);
        al.addEdge(dietikon, zurich);
        al.addEdge(lenzburg, aarau);
        al.addEdge(lenzburg, brugg);
        al.addEdge(lenzburg, dietikon);
        al.addEdge(lenzburg, wohlen);
        al.addEdge(lenzburg, zurich);
        al.addEdge(wohlen, dietikon);
        al.addEdge(wohlen, lenzburg);
        al.addEdge(wohlen, rotkreuz);
        al.addEdge(rotkreuz, wohlen);
        al.addEdge(rotkreuz, zug);
        al.addEdge(zug, rotkreuz);
        al.addEdge(zug, zurich);
        al.addEdge(zurich, dietikon);
        al.addEdge(zurich, lenzburg);
        al.addEdge(zurich, zug);
    }

    @Test
    void testAddEdge() {
        al.addEdge(wohlen, zug);
        assertTrue(al.isEdgeInGraph(wohlen, zug));
        assertFalse(al.isEdgeInGraph(zug, wohlen));
    }

    @Test
    void testAddNode() {
        final var luzern = new Node("Luzern");
        al.addNode(luzern);
        assertEquals(9, al.getCountOfNodes());
        assertTrue(al.isNodeInGraph(luzern));
    }

    @Test
    void testIsNodeInGraph() {
        assertTrue(al.isNodeInGraph(lenzburg));
    }

    @Test
    void testIsNodeInGraphNot() {
        assertFalse(al.isNodeInGraph(new Node("Luzern")));
    }

    @Test
    void testIsEdgeInGraph() {
        assertTrue(al.isEdgeInGraph(aarau, brugg));
        assertTrue(al.isEdgeInGraph(zurich, zug));
    }

    @Test
    void testIsEdgeInGraphNot() {
        assertFalse(al.isEdgeInGraph(aarau, zurich));
        assertFalse(al.isEdgeInGraph(aarau, new Node("Luzern")));
        assertFalse(al.isEdgeInGraph(new Node("Luzern"), aarau));
    }

    @Test
    void testGetCountOfNodes() {
        assertEquals(8, al.getCountOfNodes());
    }

    @Test
    void testGetNeighbours() {
        final var neigbours = al.getNeighbours(lenzburg);
        assertEquals(5, neigbours.size());
        assertTrue(neigbours.contains(aarau));
        assertTrue(neigbours.contains(brugg));
        assertTrue(neigbours.contains(dietikon));
        assertTrue(neigbours.contains(wohlen));
        assertTrue(neigbours.contains(zurich));
        assertFalse(neigbours.contains(new Node("Luzern")));
    }

    @Test
    void testGetNeighboursEmpty() {
        final var neigbours = al.getNeighbours(new Node("Luzern"));
        assertEquals(0, neigbours.size());
    }

    @Test
    void testRemoveNode() {
        al.removeNode(aarau);
        assertEquals(7, al.getCountOfNodes());
    }

    @Test
    void testRemoveEdge() {
        al.removeEdge(aarau, brugg);
        assertFalse(al.isEdgeInGraph(aarau, brugg));
    }
}
