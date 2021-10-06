package seedu.traveller.mapper;


public class Edge {
    private double weight;
    private Vertex startV;
    private Vertex targetV;

    public Edge(double w, Vertex s, Vertex t) {
        this.weight = w;
        this.startV = s;
        this.targetV = t;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Vertex getStartVertex() {
        return startV;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startV = startVertex;
    }

    public Vertex getTargetVertex() {
        return targetV;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetV = targetVertex;
    }
}