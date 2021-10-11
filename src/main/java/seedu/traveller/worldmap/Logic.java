package seedu.traveller.worldmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


public class Logic {
    private final double[][] adjMatrix = new double[10][10];

    public void computeSource(Country src, GraphList graphList) {
        ArrayList<Country> vertexArray = graphList.getVertexArray();
        for (Country v: vertexArray) {
            v.minDistance = Double.MAX_VALUE;
        }

        src.setMinDistance(0);
        PriorityQueue<Country> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(src);

        while (!priorityQueue.isEmpty()) {
            Country vertex = priorityQueue.poll();

            for (Distance distance : vertex.getDistances()) {
                Country v = distance.getEndCountry();
                double w = distance.getDistance();
                double minDist = vertex.getMinDistance() + w;

                if (minDist < v.getMinDistance()) {
                    priorityQueue.remove(vertex);
                    v.setPrevCountry(vertex);
                    v.setMinDistance(minDist);
                    priorityQueue.add(v);
                    adjMatrix[vertex.key][v.key] = minDist;
                    adjMatrix[v.key][vertex.key] = minDist;
                }
            }
        }
    }

    public MinCalcResult getToGoal(Country sourceCountry, Country targetCountry) {
        List<Country> path = new ArrayList<>();
        List<Double> dist = new ArrayList<>();

        for (Country country = targetCountry; country != null; country = country.getPrevCountry()) {
            if (!path.contains(country)) {
                path.add(country);
                if (country.getPrevCountry() != null) {
                    dist.add(adjMatrix[country.getPrevCountry().key][country.key]);
                }
                if (country == sourceCountry) {
                    break;
                }
            }
        }

        Collections.reverse(path);
        Collections.reverse(dist);

        MinCalcResult result = new MinCalcResult(sourceCountry, targetCountry, path, dist);
        return result;
    }
}