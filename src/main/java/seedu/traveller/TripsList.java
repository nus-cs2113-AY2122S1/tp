package seedu.traveller;

import java.util.ArrayList;

public class TripsList {
    private static ArrayList<Trip> trips;

    public TripsList() {
        this.trips = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        trips.add(trip);
    }

    public static Trip getTrip(int i) { return trips.get(i); }

    public static void deleteTrip(int i) { trips.remove(i); }

    public static int getSize() {
        return trips.size();
    }
}
