package seedu.traveller.worldmap;

import seedu.traveller.Parser;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

//@@author conradwee
/**
 * This class is responsible for the countries of interests as a Country class.
 */
public class Country implements Comparable<Country> {
    private static final Logger logger = Logger.getLogger(Country.class.getName());
    private final int key;
    public final String name;
    private List<Distance> distances;
    private boolean visited;
    private Country prevCountry;
    public double minDistance = Double.MAX_VALUE;


    /**
     * This is the constructor of the class Country.
     * @param name The name of the country stored as a String.
     * @param key The unique key of the country stored as an Integer.
     */
    public Country(String name, int key) {
        logger.setLevel(Level.INFO);
        this.name = name;
        this.distances = new ArrayList<>();
        this.key = key;
        logger.log(Level.INFO, "Created country: "
                + "\n\tname: " + this.name
                + "\n\tkey: " + this.key);
    }

    /**
     * This function is responsible for adding a neighbour to a given country.
     * @param distance The distance to be updated.
     * @param startCountry The starting country.
     * @param endCountry The destination country.
     */
    public static void addNeighbour(Double distance, Country startCountry, Country endCountry) {
        logger.log(Level.INFO, "Adding new neighbour: "
                + "\n\tdistance: " + distance
                + "\n\tstartCountry: " + startCountry
                + "\n\tendCountry: " + endCountry);
        startCountry.distances.add(new Distance(distance, startCountry, endCountry));
    }

    /**
     * This function is responsible for updating the distance between a given neighbour and the given country of
     * interest.
     * @param newDistance The new distance to be updated.
     * @param startCountry The starting country.
     * @param endCountry The destination country.
     */
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
