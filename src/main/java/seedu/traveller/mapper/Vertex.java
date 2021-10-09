package seedu.traveller.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex implements Comparable<Vertex> {
    public int key;
    public final String name;
    private List<Edge> edges;
    private boolean visited;
    private Vertex prevVertex;
    public double minDistance = Double.MAX_VALUE;


    public Vertex(String name, int key) {
        this.name = name;
        this.edges = new ArrayList<>();
        this.key = key;
        //System.out.println("Node = " + this.name + ", Key = " + this.key);
    }

    public static void addNeighbour(Double w, Vertex v1, Vertex v2) {
        v1.edges.add(new Edge(w,v1,v2));
    }

    public static void updateNeighbour(Double w, Vertex v1, Vertex v2) {
        for (Edge e : v1.getEdges()) {
            if (e.getTargetVertex().key == v2.key) {
                e.setWeight(w);
            }
        }
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Vertex getPrevVertex() {
        return prevVertex;
    }

    public void setPrevVertex(Vertex prevVertex) {
        this.prevVertex = prevVertex;
    }

    public double getMinDistance() {
        return minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int compareTo(Vertex otherVertex) {
        return Double.compare(this.minDistance, otherVertex.minDistance);
    }

    public int key() {
        return this.key;
    }
}
