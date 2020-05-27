package ch.hslu.ad.sw15;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class AdjazenzMatrixTest {
    private static final Logger LOG = LogManager.getLogger(AdjazenzMatrixTest.class);
    private ArrayList<String> nodeNames = new ArrayList<>(Arrays.asList(
            "Aarau", "Brugg", "Dietikon", "Lenzburg", "Wohlen", "Rotkreuz", "Zug", "Zurich"));
    private boolean[][] matrix = {
            {false,    true,    false,      true,       false,    false,     false,  false}, //Aarau
            {true,     false,   true,       true,       false,    false,     false,  false}, //Brugg
            {false,    true,    false,      true,       true,     false,     false,  true }, //Dietikon
            {true,     true,    true,       false,      true,     false,     false,  true }, //Lenzburg
            {false,    false,   true,       true,       false,    true,      false,  false}, //Wohlen
            {false,    false,   false,      false,      true,     false,     true,   false}, //Rotkreuz
            {false,    false,   false,      false,      false,    true,      false,  true }, //Zug
            {false,    false,   true,       true,       false,    false,     true,   false}, //Zurich
    };

    @Test
    void testAddNode() {
        final AdjazenzMatrix am = new AdjazenzMatrix(nodeNames, matrix);
        final var neighbours = new ArrayList<>(Arrays.asList("Lenzburg", "Rotkreuz"));
        LOG.info(am.toString());
        am.addNode("Luzern", neighbours);
        LOG.info(am.toString());
        assertEquals(9, am.getCountOfNodes());
        assertEquals(14, am.getCountOfEdges());
        assertTrue(am.isConnectedDirectly("Luzern", "Lenzburg"));
        assertTrue(am.isConnectedDirectly("Luzern", "Rotkreuz"));
        assertFalse(am.isConnectedDirectly("Luzern", "Zug"));
    }

    @Test
    void testGetCountOfNodes() {
        final AdjazenzMatrix am = new AdjazenzMatrix(nodeNames, matrix);
        assertEquals(8, am.getCountOfNodes());
    }

    @Test
    void testGetCountOfEdges() {
        final AdjazenzMatrix am = new AdjazenzMatrix(nodeNames, matrix);
        assertEquals(12, am.getCountOfEdges());
    }

    @Test
    void testHasDirectLine() {
        final AdjazenzMatrix am = new AdjazenzMatrix(nodeNames, matrix);
        assertTrue(am.isConnectedDirectly("Zug", "Rotkreuz"));
        assertFalse(am.isConnectedDirectly("Brugg", "Zurich"));
    }

    @Test
    void testHasDirectLineThrows() {
        final AdjazenzMatrix am = new AdjazenzMatrix(nodeNames, matrix);
        assertThrows(IllegalArgumentException.class, () -> am.isConnectedDirectly("Zug", "sd"));
        assertThrows(IllegalArgumentException.class, () -> am.isConnectedDirectly("", "Zurich"));
    }

    @Test
    void testGetDirectConnections() {
        final AdjazenzMatrix am = new AdjazenzMatrix(nodeNames, matrix);
        final var stations = am.getDirectConnections("Aarau");
        assertEquals(2, stations.size());
        assertTrue(stations.contains("Lenzburg"));
        assertTrue(stations.contains("Brugg"));
        assertFalse(stations.contains("Dietikon"));
    }

    @Test
    void testGetDirectConnectionsThrows() {
        final AdjazenzMatrix am = new AdjazenzMatrix(nodeNames, matrix);
        assertThrows(IllegalArgumentException.class, () -> am.getDirectConnections(""));
        assertThrows(IllegalArgumentException.class, () -> am.getDirectConnections("asdfasdf"));
    }

    @Test
    void testRemoveNode() {
        final AdjazenzMatrix am = new AdjazenzMatrix(nodeNames, matrix);
        final String nodeToRemove = "Wohlen";
        LOG.info(am.toString());
        am.removeNode(nodeToRemove);
        LOG.info(am.toString());
        assertEquals(7, am.getCountOfNodes());
        assertEquals(9, am.getCountOfEdges());
        assertThrows(IllegalArgumentException.class, () -> am.getDirectConnections(nodeToRemove));
    }

    @Test
    void testRemoveTwoNodes() {
        final AdjazenzMatrix am = new AdjazenzMatrix(nodeNames, matrix);
        LOG.info(am.toString());
        am.removeNode("Wohlen");
        am.removeNode("Lenzburg");
        LOG.info(am.toString());
        assertEquals(6, am.getCountOfNodes());
        assertEquals(5, am.getCountOfEdges());
    }

    @Test
    void testRemoveNodeThrows() {
        final AdjazenzMatrix am = new AdjazenzMatrix(nodeNames, matrix);
        final String nodeToRemove = "Sempach";
        assertThrows(IllegalArgumentException.class, () -> am.removeNode(nodeToRemove));
    }
}
