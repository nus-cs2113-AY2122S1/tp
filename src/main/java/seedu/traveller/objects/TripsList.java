package seedu.traveller.objects;

import seedu.traveller.exceptions.TravellerException;
import seedu.traveller.exceptions.TripNotFoundException;

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

    public void addTrip(Trip trip) throws TravellerException {
        String tripName = trip.getTripName();
        for (int i = 0; i < getSize(); i++) {
            Trip existingTrip = getTrip(i);
            assert !tripName.equals(existingTrip.getTripName()) : "TripName is already in use.";
        }
        this.trips.add(trip);
    }

    public Trip getTrip(int tripNumber) throws TravellerException {
        if (tripNumber < 0 || tripNumber >= getSize()) {
            throw new TripNotFoundException();
        }
        return trips.get(tripNumber);
    }

    public Trip getTrip(String tripName) throws TravellerException {
        assert !tripName.equals("all") : "'all' is an invalid tripName.";
        assert !tripName.equals("") : "'' is an invalid tripName.";
        int tripIndex = getTripIndex(tripName);
        return getTrip(tripIndex);
    }

    public int getTripIndex(String tripName) throws TravellerException {
        int tripIndex = -1;  // If trip is not found, return -1 to indicate so.
        for (int i = 0; i < getSize(); i++) {
            Trip trip = getTrip(i);
            if (tripName.equals(trip.getTripName())) {
                tripIndex = i;
                break;
            }
        }
        return tripIndex;
    }

    public void deleteTrip(int tripNumber) {
        assert tripNumber < getSize() : "tripNumber is larger than size of trips array.";
        assert tripNumber >= 0 : "tripNumber is negative.";
        trips.remove(tripNumber);
    }

    public int getSize() {
        return trips.size();
    }
}
