package seedu.traveller;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class TripsList {
    private static final Logger logger = Logger.getLogger(TripsList.class.getName());
    private final ArrayList<Trip> trips;

    public TripsList() {
        logger.setLevel(Level.INFO);
        logger.log(Level.FINE, "Created a trips list");
        this.trips = new ArrayList<>();
    }

    public void addTrip(Trip trip) {
        this.trips.add(trip);
    }

    public Trip getTrip(int i) {
        return this.trips.get(i);
    }

    public Trip getTrip(String tripName) {
        int tripIndex = getTripIndex(tripName);
        return getTrip(tripIndex);
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
        this.trips.remove(i);
    }

    public int getSize() {
        return this.trips.size();
    }
}
