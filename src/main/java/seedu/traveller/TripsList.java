package seedu.traveller;

import java.util.ArrayList;

public class TripsList {
    private final ArrayList<Trip> trips;

    public TripsList() {
        this.trips = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public Trip getTrip(int i) {
        return trips.get(i);
    }

    public int getSize() {
        return trips.size();
    }
}
