package seedu.traveller;

import java.util.ArrayList;

public class Trip {
    private final String tripName;
    private final ArrayList<Destination> destinationsList;

    public Trip(String tripName) {
        this.tripName = tripName;
        this.destinationsList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "\t\tTripName: " + getTripName();
    }

    public String getTripName() {
        return this.tripName;
    }
}
