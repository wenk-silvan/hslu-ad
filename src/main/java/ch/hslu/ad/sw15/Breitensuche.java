package ch.hslu.ad.sw15;

import com.google.common.graph.Graph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;

public class Breitensuche {
    private static final Logger LOG = LogManager.getLogger(Breitensuche.class);

    public static void bfs(final AdjazenzListe g, final Node start) {
        Queue<Node> wait = new LinkedList<>(); // FIFO-Queue
        start.color = Color.GRAY;
        wait.add(start); // put
        while (!wait.isEmpty()) {
            var node = wait.remove(); // get
            LOG.info(node);
            node.color = Color.BLACK;
            for (var n : g.getNeighbours(node)) {
                if (n.color.equals(Color.WHITE)) {
                    n.color = Color.GRAY;
                    wait.add(n); // put
                }
            }
        }
    }
}
