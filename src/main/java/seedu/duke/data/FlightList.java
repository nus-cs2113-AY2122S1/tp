package seedu.duke.data;

import seedu.duke.Ui;
import seedu.duke.data.Flight;

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

    public int getFlightCount() {
        return flightCount;
    }

    public Flight getFlight(int index) {
        return flights.get(index);
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
