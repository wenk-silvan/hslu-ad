package ch.hslu.ad.sw15;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.LinkedList;

public class Tiefensuche {
    private static final Logger LOG = LogManager.getLogger(Tiefensuche.class);

    public static void dfs(final AdjazenzListe g, final Node start) {
        LinkedList<Node> wait = new LinkedList<>(); // Stack bzw. LIFO-Queue
        boolean found;
        start.color = Color.GRAY;
        wait.add(0, start); // push
        while (!wait.isEmpty()) {
            var node = wait.getFirst(); // top
            found = false;
            for (var n : g.getNeighbours(node)) {
                if (n.color.equals(Color.WHITE)) {
                    n.color = Color.GRAY;
                    wait.add(0, n); // push;
                    found = true;
                    break;
                }
            }
            if (!found) {
                node.color = Color.BLACK;
                LOG.info(node);
                wait.remove(); // pop
            }
        }
    }

    public static void dfsRecursive(final AdjazenzListe al, final Node start) {
        start.color = Color.GRAY;
        for (var n : al.getNeighbours(start)) {
            if (n.color.equals(Color.WHITE)) {
                dfsRecursive(al, n);
            }
        }
        start.color = Color.BLACK;
        LOG.info(start);
    }
}
