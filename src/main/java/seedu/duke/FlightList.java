package seedu.duke;

import java.util.ArrayList;

public class FlightList {
    private static ArrayList<Flight> flights;
    private static int flightCount = 0;

    public FlightList() {
        flights = new ArrayList<>();
        flightCount = 0;
    }

    public void add(Flight flight, Ui ui) {
        flights.add(flight);
        flightCount++;
    }

    public Flight getFlight(String flightId) {
        for (int i = 0; i < flightCount; i++) {
            if (flights.get(i).getId().equals(flightId)) {
                return flights.get(i);
            }
        }
        return null;
    }

}
