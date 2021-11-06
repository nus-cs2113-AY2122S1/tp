package seedu.traveller.worldmap;

import seedu.traveller.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Country implements Comparable<Country> {
    private static final Logger logger = Logger.getLogger(Country.class.getName());
    private final int key;
    public final String name;
    private List<Distance> distances;
    private boolean visited;
    private Country prevCountry;
    public double minDistance = Double.MAX_VALUE;


    public Country(String name, int key) {
        logger.setLevel(Level.INFO);
        this.name = name;
        this.distances = new ArrayList<>();
        this.key = key;
        logger.log(Level.INFO, "Created country: "
                + "\n\tname: " + this.name
                + "\n\tkey: " + this.key);
    }

    public static void addNeighbour(Double distance, Country startCountry, Country endCountry) {
        logger.log(Level.INFO, "Adding new neighbour: "
                + "\n\tdistance: " + distance
                + "\n\tstartCountry: " + startCountry
                + "\n\tendCountry: " + endCountry);
        startCountry.distances.add(new Distance(distance, startCountry, endCountry));
    }

    public static void updateNeighbour(Double newDistance, Country startCountry, Country endCountry) {
        logger.log(Level.INFO, "Updating neighbour: "
                + "\n\tnewDistance: " + newDistance
                + "\n\tstartCountry: " + startCountry
                + "\n\tendCountry: " + endCountry);
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
