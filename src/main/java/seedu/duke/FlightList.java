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

    public int getFlightCount() {
        return flightCount;
    }

    public Flight getFlight(int index) {
        return flights.get(index);
    }

}
