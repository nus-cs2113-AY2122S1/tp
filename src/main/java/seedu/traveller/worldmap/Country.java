package seedu.traveller.worldmap;

import java.util.ArrayList;
import java.util.List;


public class Country implements Comparable<Country> {
    public int key;
    public final String name;
    private List<Distance> distances;
    private boolean visited;
    private Country prevCountry;
    public double minDistance = Double.MAX_VALUE;


    public Country(String name, int key) {
        this.name = name;
        this.distances = new ArrayList<>();
        this.key = key;
    }

    public static void addNeighbour(Double distance, Country startCountry, Country endCountry) {
        startCountry.distances.add(new Distance(distance, startCountry, endCountry));
    }

    public static void updateNeighbour(Double newDistance, Country startCountry, Country endCountry) {
        int endCountryKey = endCountry.getKey();
        for (Distance distance : startCountry.getDistances()) {
            if (distance.getEndCountry().getKey() == endCountryKey) {
                distance.setDistance(newDistance);
            }
        }
    }

    public List<Distance> getDistances() {
        return this.distances;
    }

    public void setDistances(List<Distance> distances) {
        this.distances = distances;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Country getPrevCountry() {
        return this.prevCountry;
    }

    public void setPrevCountry(Country prevCountry) {
        this.prevCountry = prevCountry;
    }

    public double getMinDistance() {
        return this.minDistance;
    }

    public void setMinDistance(double minDistance) {
        this.minDistance = minDistance;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(Country otherCountry) {
        return Double.compare(this.minDistance, otherCountry.minDistance);
    }

    public int getKey() {
        return this.key;
    }
}
