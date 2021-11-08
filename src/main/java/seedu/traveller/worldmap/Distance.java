package seedu.traveller.worldmap;


//@@author jach23

import seedu.traveller.worldmap.exceptions.EmptyVertexException;

/**
 * This class is responsible for storing the distances between the two countries of interest.
 */
public class Distance {
    private double distance;
    private Country startCountry;
    private Country endCountry;

    /**
     * This is the constructor of the class Distance.
     * @param distance The value of the associated edge between the starting and destination countries.
     * @param startCountry The starting country.
     * @param endCountry The destination country.
     */
    public Distance(double distance, Country startCountry, Country endCountry) {
        this.distance = distance;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
    }

    public Country getStartCountry() {
        return this.startCountry;
    }

    public Country getEndCountry() {
        return this.endCountry;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setStartCountry(Country startCountry) {
        this.startCountry = startCountry;
    }

    public void setEndCountry(Country endCountry) {
        this.endCountry = endCountry;
    }
}