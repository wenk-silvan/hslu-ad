package ch.hslu.ad.sw15;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class AdjazenzMatrix {
    private ArrayList<String> nodeNames;
    private boolean[][] matrix;

    AdjazenzMatrix(final ArrayList<String> nodeNames, final boolean[][] matrix) {
        this.nodeNames = nodeNames;
        this.matrix = matrix;
    }

    public void addNode(String name, Collection<String> neighbours) {
        neighbours.forEach(n -> {
          if (!this.nodeNames.contains(n)) {
              throw new IllegalArgumentException(String.format("The neighbour '%s' does not exist.", n));
          }
        });

        boolean[][] matrix = new boolean[this.matrix.length + 1][];
        for (int i = 0; i < this.matrix.length; i++) {
            matrix[i] = Arrays.copyOf(this.matrix[i], this.matrix.length + 1);
        }
        matrix[this.matrix.length] = new boolean[this.matrix.length + 1];
        this.nodeNames.add(name);
        for (int i = 0; i < matrix.length - 1; i++) {
            final var isDirectlyConnected = (neighbours.contains(this.getNodeName(i)));
            matrix[i][matrix.length - 1] = isDirectlyConnected;
        }
        for (int i = 0; i < matrix.length; i++) {
            matrix[matrix.length - 1][i] = matrix[i][matrix.length - 1];
        }
        this.matrix = matrix;
    }

    public int getCountOfEdges() {
        int count = 0;
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                if (this.matrix[i][j]) count++;
            }
        }
        return count / 2;
    }

    public int getCountOfNodes() {
        return this.nodeNames.size();
    }

    public ArrayList<String> getNeighbours(final String node) {
        final ArrayList<String> neighbours = new ArrayList<>();
        final int index = this.getIndexOfNode(node);
        for (int i = 0; i < this.matrix[index].length; i++) {
            if (this.matrix[index][i]) {
                neighbours.add(this.getNodeName(i));
            }
        }
        return neighbours;
    }

    public String getNodeName(final int index) {
        return this.nodeNames.get(index);
    }

    public boolean areNeighbours(final String from, final String to) {
        if (!this.nodeNames.contains(from)) {
            throw new IllegalArgumentException(String.format("From '%s' does not exist.", from));
        } else if (!this.nodeNames.contains(to)) {
            throw new IllegalArgumentException(String.format("To '%s' does not exist.", to));
        }
        final int indexFrom = this.nodeNames.indexOf(from);
        final int indexTo = this.nodeNames.indexOf(to);
        return this.isEdge(indexFrom, indexTo);
    }

    public boolean isEdge(final int i, final int j) {
        return this.matrix[i][j];
    }

    public void removeNode(String node) {
        final int nodeIndex = this.getIndexOfNode(node);
        var matrix = new boolean[this.matrix.length - 1][this.matrix.length - 1];
        for (int i = 0; i < nodeIndex; i++) {
            for (int j = 0; j < nodeIndex; j++) {
                matrix[i][j] = this.matrix[i][j];
            }
            for (int j = this.matrix.length - 1; j > nodeIndex; j--) {
                matrix[i][j - 1] = this.matrix[i][j];
            }
        }
        for (int i = this.matrix.length - 1; i > nodeIndex; i--) {
            for (int j = 0; j < nodeIndex; j++) {
                matrix[i - 1][j] = this.matrix[i][j];
            }
            for (int j = this.matrix.length - 1; j > nodeIndex; j--) {
                matrix[i - 1][j - 1] = this.matrix[i][j];
            }
        }
        this.nodeNames.remove(node);
        this.matrix = matrix;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();
        sb.append("\n");
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix[i].length; j++) {
                sb.append(String.format("[%s] ", this.matrix[i][j] ? 1 : 0));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private int getIndexOfNode(String node) {
        final int index = this.nodeNames.indexOf(node);
        if (index < 0) {
            throw new IllegalArgumentException(String.format("The given node '%s' does not exist.", node));
        }
        return index;
    }
}
