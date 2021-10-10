package seedu.traveller.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import static seedu.traveller.mapper.GraphList.vertexArray;


public class Dijkstra {
    private static double[][] adjMatrix = new double[10][10];
    //double[][] adjMatrix ;

    public static void computeSource(Vertex src) {
        for (Vertex v: vertexArray) {
            v.minDistance = Double.MAX_VALUE;
        }

        src.setMinDistance(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(src);

        while (!priorityQueue.isEmpty()) {
            Vertex vertex = priorityQueue.poll();

            for (Edge edge : vertex.getEdges()) {
                Vertex v = edge.getTargetVertex();
                double w = edge.getWeight();
                double minDist = vertex.getMinDistance() + w;

                if (minDist < v.getMinDistance()) {
                    priorityQueue.remove(vertex);
                    v.setPrevVertex(vertex);
                    v.setMinDistance(minDist);
                    priorityQueue.add(v);
                    adjMatrix[vertex.key][v.key] = minDist;
                }
            }
        }
    }

    public static List<Vertex> getToGoal(Vertex targetV) {
        List<Vertex> path = new ArrayList<>();
        List<Double> dist = new ArrayList<>();
        double curr;
        double sum = 0.0;

        for (Vertex v = targetV; v != null; v = v.getPrevVertex()) {
            path.add(v);
            if (v.getPrevVertex() != null) {
                dist.add(adjMatrix[v.getPrevVertex().key][v.key]);
            }
        }
        Collections.reverse(path);
        Collections.reverse(dist);

        System.out.println("Shortest path from source to goal: " + path);

        System.out.println("Breakdown of path");
        for (double d : dist) {
            curr = d - sum;
            System.out.println(curr);
            sum += curr;
        }
        System.out.println("Total distance = " + sum);
        return path;
    }

    public static List<Vertex> run(Vertex s, Vertex t) {
        computeSource(s);
        return getToGoal(t);
    }
}