package seedu.traveller;

import java.util.ArrayList;

public class Trip {
    private final String tripName;
    private String startCountry;
    private String endCountry;
    private final ArrayList<Destination> destinationsList;

    public Trip(String tripName, String startCountry, String endCountry) {
        this.tripName = tripName;
        this.startCountry = startCountry;
        this.endCountry = endCountry;
        this.destinationsList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "\t\tTripName: " + getTripName() + "\n\t\t\t Origin: " + getStartCountry()+ "\n\t\t\t Destination: "+ getEndCountry();
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