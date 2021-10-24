package seedu.duke;

import java.util.ArrayList;

public class FlightList {
    private static final String FLIGHT_NOT_FOUND_MESSAGE = "Flight ID cannot be found. Please try another Flight ID.";

    private static ArrayList<Flight> flights;
    private static int flightCount = 0;

    public FlightList() {
        flights = new ArrayList<>();
        flightCount = 0;
    }

    public void add(Flight flight) {
        flights.add(flight);
        flightCount++;
    }

    public void cut(Flight flight) {
        flights.remove(flight);
        flightCount--;
    }

    public int getFlightCount() {
        return flightCount;
    }

    public Flight getFlightById(int index) {
        return flights.get(index);
    }
  
    public Flight getFlightById(String flightId) throws TourPlannerException {
        for (int i = 0; i < flightCount; i++) {
            if (flights.get(i).getId().equals(flightId)) {
                return flights.get(i);
            }
        }
        throw new TourPlannerException(FLIGHT_NOT_FOUND_MESSAGE);
    }

}
