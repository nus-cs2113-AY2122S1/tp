package seedu.traveller.mapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

import static seedu.traveller.mapper.GraphList.vertexArray;


public class Dijkstra {
    private static double[][] adjMatrix = new double[10][10];

    public static void computeSource(Vertex src) {
        for (Vertex v: vertexArray) {
            v.minDistance = Double.MAX_VALUE;
        }

        src.setMinDistance(0);
        PriorityQueue<Vertex> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(src);

        while (!priorityQueue.isEmpty()) {
            Vertex vertex = priorityQueue.poll();
            //System.out.println("Scanning Vertex " + vertex.name);

            for (Edge edge : vertex.getEdges()) {
                //System.out.println("Scanning Edge from " + edge.getStartVertex() + " to " + edge.getTargetVertex());
                Vertex v = edge.getTargetVertex();
                double w = edge.getWeight();
                double minDist = vertex.getMinDistance() + w;

                if (minDist < v.getMinDistance()) {
                    priorityQueue.remove(vertex);
                    v.setPrevVertex(vertex);
                    v.setMinDistance(minDist);
                    priorityQueue.add(v);
                    adjMatrix[vertex.key][v.key] = minDist;
                    adjMatrix[v.key][vertex.key] = minDist;
                    //System.out.println("Min distance of " + minDist + " between " + vertex.name + " and " + v.name);
                }
            }
        }
    }

    public static void getToGoal(Vertex srcV, Vertex targetV) {
        List<Vertex> path = new ArrayList<>();
        List<Double> dist = new ArrayList<>();
        double curr;
        double sum = 0.0;

        for (Vertex v = targetV; v != null; v = v.getPrevVertex()) {
            if (!path.contains(v)) {
                path.add(v);
                //System.out.println("Adding to shortest path Vertex " + v.name);
                if (v.getPrevVertex() != null) {
                    dist.add(adjMatrix[v.getPrevVertex().key][v.key]);
                }
                if (v == srcV) {
                    break;
                }
            }
        }


        Collections.reverse(path);
        Collections.reverse(dist);

        System.out.println("Shortest path from source to goal: " + path);

        System.out.println("Breakdown of path");
        for (double d : dist) {
            curr = d - sum;
            if (curr - 0.0 > 0.000001) {
                System.out.println(curr);
            }
            sum += curr;
        }
        System.out.println("Total distance = " + sum);
    }

    public static void run(Vertex s, Vertex t) {
        computeSource(s);
        getToGoal(s,t);
    }

}