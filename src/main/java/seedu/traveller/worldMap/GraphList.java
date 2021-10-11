package seedu.traveller.worldMap;

import seedu.traveller.worldMap.exceptions.EmptyVertexException;

import java.util.ArrayList;
import java.util.Objects;


public class GraphList {
    private final ArrayList<Country> vertexArray;
    private final ArrayList<String> nameArray;
    private final double[][] edgeMatrix;

    public GraphList() {
        this.vertexArray = new ArrayList<>();
        this.nameArray = new ArrayList<>();
        this.edgeMatrix = new double[10][10];
    }

    public ArrayList<Country> getVertexArray() {
        return this.vertexArray;
    }

    public ArrayList<String> getNameArray() {
        return this.nameArray;
    }

    public double[][] getEdgeMatrix() {
        return this.edgeMatrix;
    }

    public void addVertex(Country v) {
        this.vertexArray.add(v);
        this.nameArray.add(v.name);
    }

    public boolean doesContains(String data) {
        return this.nameArray.contains(data);
    }

    public Country findVertex(String s) throws EmptyVertexException {
        for (Country v: this.vertexArray) {
            if (Objects.equals(v.name, s)) {
                return v;
            }
        }
        throw new EmptyVertexException();
    }

    public void modifyEdge(Double w, Country v1, Country v2) {
        int s = v1.key;
        int t = v2.key;
        edgeMatrix[s][t] = w;
        edgeMatrix[t][s] = w;
        System.out.println("Edge between " + s + " and " + t + " updated to " + w);
        Country.updateNeighbour(w, v1, v2);
        Country.updateNeighbour(w, v2, v1);
    }

    public void createEdge(Double w, Country v1, Country v2) {
        int s = v1.key;
        int t = v2.key;
        edgeMatrix[s][t] = w;
        edgeMatrix[t][s] = w;
        Country.addNeighbour(w, v1, v2);
        Country.addNeighbour(w, v2, v1);
    }

    public boolean edgeExists(Country s, Country t) {
        return edgeMatrix[s.key][t.key] > 0.0001;  // floating point exception
    }
}
