package seedu.traveller.mapper;

import java.util.ArrayList;
import java.util.Objects;

public class GraphList {
    public static ArrayList<Vertex> vertexArray = new ArrayList<>();
    public static ArrayList<String> nameArray = new ArrayList<>();
    private static double[][] edgeMatrix = new double[10][10];

    public static void addVertex(Vertex v) {
        vertexArray.add(v);
        nameArray.add(v.name);
    }

    public static boolean doesContains(String data) {
        return nameArray.contains(data);
    }

    public static Vertex findVertex(String s) throws EmptyVertexException {
        for (Vertex v: vertexArray) {
            if (Objects.equals(v.name, s)) {
                return v;
            }
        }
        throw new EmptyVertexException();
    }

    public static void modifyEdge(Double w, Vertex v1, Vertex v2) {
        int s = v1.key;
        int t = v2.key;
        edgeMatrix[s][t] = w;
        edgeMatrix[t][s] = w;
        System.out.println("Edge between " + s + " and " + t + " updated to " + w);
        Vertex.updateNeighbour(w, v1, v2);
        // Vertex.updateNeighbour(w, v2, v1);
    }

    public static void createEdge(Double w, Vertex v1, Vertex v2) {
        int s = v1.key;
        int t = v2.key;
        edgeMatrix[s][t] = w;
        edgeMatrix[t][s] = w;
        System.out.println("Edge between " + s + " and " + t + " created and assigned to " + w);
        Vertex.addNeighbour(w, v1, v2);
        // Vertex.addNeighbour(w, v2, v1);
    }

    public static boolean edgeExists(Vertex s, Vertex t) {
        return edgeMatrix[s.key][t.key] > 0.0001; //floating point exception
    }
}
