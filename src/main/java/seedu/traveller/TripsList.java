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

    public int getTripIndex(String tripName) {
        int tripIndex = -1;
        for (int i = 0; i < this.getSize(); i++) {
            Trip trip = this.getTrip(i);
            if (tripName.equals(trip.getTripName())) {
                tripIndex = i;
                break;
            }
        }
        return tripIndex;
    }

    public void deleteTrip(int i) {
        trips.remove(i);
    }

    public int getSize() {
        return trips.size();
    }
}
