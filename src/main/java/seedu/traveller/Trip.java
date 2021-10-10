package seedu.traveller;

import seedu.traveller.mapper.Vertex;

import java.util.ArrayList;
import java.util.List;

public class Trip {
    private final String tripName;
    private String startCountry;
    private String endCountry;
    private List<Vertex> path;
    private final ArrayList<Destination> destinationsList;

    public Trip(String tripName, String startCountry, String endCountry, List<Vertex> path) {
        this.tripName = tripName;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.path = path;
        this.destinationsList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "\t\tTripName: " + getTripName() + "\n\t\t\t Origin: "
                + getStartCountry() + "\n\t\t\t Destination: " + getEndCountry()
                + "\n\t\t\t Path: " + path;
    }

    public String getTripName() {
        return this.tripName;
    }

    public String getStartCountry() {
        return this.startCountry;
    }

    public String getEndCountry() {
        return this.endCountry;
    }

}