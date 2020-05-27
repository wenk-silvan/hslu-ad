package ch.hslu.ad.sw15;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AdjazenzListe {
    private final Map<Node, Set<Node>> map;

    public AdjazenzListe() {
        this.map = new HashMap<>();
    }

    public boolean addEdge(final Node from, final Node to) {
        if (!isNodeInGraph(from) || !isNodeInGraph(to)) return false;
        if (isEdgeInGraph(from, to)) return false;
        return map.get(from).add(to);
    }

    public boolean addNode(final Node node) {
        if (isNodeInGraph(node)) return false;
        map.put(node, new HashSet<>());
        return true;
    }

    public boolean isEdgeInGraph(final Node from, final Node to) {
        if (!isNodeInGraph(from)) return false;
        return map.get(from).contains(to);
    }

    public boolean isNodeInGraph(final Node node) {
        return map.containsKey(node);
    }

    public int getCountOfNodes() {
        return this.map.size();
    }

    public Set<Node> getNeighbours(final Node node) {
        if (isNodeInGraph(node)) {
            return map.get(node);
        } else {
            return new HashSet<>();
        }
    }

    public boolean removeEdge(final Node from, final Node to) {
        if (!isNodeInGraph(from)) return false;
        if (!isNodeInGraph(to)) return false;
        this.map.get(from).remove(to);
        return true;
    }

    public boolean removeNode(final Node node) {
        if (!isNodeInGraph(node)) return false;
        this.map.remove(node);
        return true;
    }
}