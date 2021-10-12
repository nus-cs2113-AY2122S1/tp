package seedu.traveller;

import seedu.traveller.worldmap.Country;

import java.util.List;


public class Trip {
    private final String tripName;
    private final String startCountryCode;
    private final String endCountryCode;
    private final List<Country> path;
    private final List<Double> distances;

    public Trip(String tripName, String startCountryCode, String endCountryCode,
                List<Country> path, List<Double> distances) {
        this.tripName = tripName;
        this.startCountryCode = startCountryCode;
        this.endCountryCode = endCountryCode;
        this.path = path;
        this.distances = distances;
    }

    @Override
    public String toString() {
        return "\t\tTripName: " + getTripName()
                + "\n\t\t\t Origin: " + getStartCountryCode()
                + "\n\t\t\t Destination: " + getEndCountryCode()
                + "\n\t\t\t Path: " + getPath();
    }

    public String getTripName() {
        return this.tripName;
    }

    public String getStartCountryCode() {
        return this.startCountryCode;
    }

    public String getEndCountryCode() {
        return this.endCountryCode;
    }

    public List<Country> getPath() {
        return this.path;
    }

    public List<Double> getDistances() {
        return this.distances;
    }

}