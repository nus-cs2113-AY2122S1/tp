package seedu.duke;

import java.util.ArrayList;

public class FlightList {
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

}
