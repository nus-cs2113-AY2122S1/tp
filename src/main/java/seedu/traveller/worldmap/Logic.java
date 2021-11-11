package seedu.traveller.worldmap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

//@@author jach23
/**
 * This class is the key logic of the World Map function by implementing
 * Dijkstra's algorithm.
 */
public class Logic {
    private final double[][] adjMatrix = new double[10][10];

    /**
     * This function is responsible for implementing Dijkstra's algorithm and searching for the shortest paths from the
     * given starting country to all other countries.
     * @param src The starting country.
     * @param graphList The set of distances and countries stored as vertex and edges respectively.
     */
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
                    adjMatrix[vertex.getKey()][v.getKey()] = minDist;
                    adjMatrix[v.getKey()][vertex.getKey()] = minDist;
                }
            }
        }
    }

    /**
     * This function is responsible for finding the shortest path from the source to the target country, where the
     * shortest path refers to the path of least time or smallest cost.
     * @param sourceCountry The starting country.
     * @param targetCountry The destination country.
     * @return MinCalcResult which contains the source and destination countries along with the shortest path.
     */
    public MinCalcResult getToGoal(Country sourceCountry, Country targetCountry) {
        List<Country> path = new ArrayList<>();
        List<Double> accumDist = new ArrayList<>();
        List<Double> dist = new ArrayList<>();
        double curr;
        double sum = 0.0;

        for (Country country = targetCountry; country != null; country = country.getPrevCountry()) {
            if (!path.contains(country)) {
                path.add(country);
                if (country.getPrevCountry() != null) {
                    accumDist.add(adjMatrix[country.getPrevCountry().getKey()][country.getKey()]);
                }
                if (country == sourceCountry) {
                    break;
                }
            }
        }

        Collections.reverse(path);
        Collections.reverse(accumDist);

        for (double d : accumDist) {
            curr = d - sum;
            if (curr - 0.0 > 0.000001) {
                dist.add(curr);
            }
            sum += curr;
        }

        MinCalcResult result = new MinCalcResult(sourceCountry, targetCountry, path, dist);
        return result;
    }
}