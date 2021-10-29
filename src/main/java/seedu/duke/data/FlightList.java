package seedu.duke.data;

import seedu.duke.TourPlannerException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FlightList {
    private static final String FLIGHT_NOT_FOUND_MESSAGE = "Flight ID cannot be found. Please try another Flight ID.";

    private static ArrayList<Flight> flights;
    private static ArrayList<String> flightIds;
    private static ArrayList<String> flightReturnDates;
    private static ArrayList<String> flightDepartureDates;
    private static ArrayList<String> iteratedFlightIds;
    private static int flightCount = 0;

    private static final Comparator<String> dateTimeStringComparator = new Comparator<String>() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yy HH:mm");

        @Override
        public int compare(String dateTimeStringOne, String dateTimeStringTwo) {
            LocalDateTime dateTimeOne = LocalDateTime.parse(dateTimeStringOne, formatter);
            LocalDateTime dateTimeTwo = LocalDateTime.parse(dateTimeStringTwo, formatter);
            if (dateTimeOne.isBefore(dateTimeTwo)) {
                return -1;
            } else if (dateTimeOne.isEqual(dateTimeTwo)) {
                return 0;
            } else {
                return 1;
            }
        }
    };

    public FlightList() {
        flights = new ArrayList<>();
        flightIds = new ArrayList<>();
        flightReturnDates = new ArrayList<>();
        flightDepartureDates = new ArrayList<>();
        flightCount = 0;
    }

    public void add(Flight flight) {
        flights.add(flight);
        flightIds.add(flight.getId());
        flightReturnDates.add(flight.getReturnDate());
        flightDepartureDates.add(flight.getDepartDate());
        flightCount++;
    }

    public void cut(Flight flight) {
        flights.remove(flight);
        flightCount--;
    }

    public int getFlightCount() {
        return flightCount;
    }

    public Flight getFlightByIndex(int index) {
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

    public Flight getFlightByArriveDate(String flightReturnDate) throws TourPlannerException {
        for (Flight currFlight : flights) {
            String flightId = currFlight.getId();
            String currFlightReturnDate = currFlight.getReturnDate();
            if (currFlightReturnDate.equals(flightReturnDate) && !iteratedFlightIds.contains(flightId)) {
                iteratedFlightIds.add(flightId);
                return currFlight;
            }
        }
        throw new TourPlannerException(FLIGHT_NOT_FOUND_MESSAGE);
    }

    public Flight getFlightByDepartDate(String flightDepartDate) throws TourPlannerException {
        for (Flight currFlight : flights) {
            String flightId = currFlight.getId();
            String currFlightDepartDate = currFlight.getDepartDate();
            if (currFlightDepartDate.equals(flightDepartDate) && !iteratedFlightIds.contains(flightId)) {
                iteratedFlightIds.add(flightId);
                return currFlight;
            }
        }
        throw new TourPlannerException(FLIGHT_NOT_FOUND_MESSAGE);
    }

    public ArrayList<String> getSortedFlightIds() {
        Collections.sort(flightIds);
        return flightIds;
    }

    public ArrayList<String> getSortedReturnDates() {
        flightReturnDates.sort(dateTimeStringComparator);
        return flightReturnDates;
    }

    public ArrayList<String> getSortedDepartDates() {
        flightDepartureDates.sort(dateTimeStringComparator);
        return flightDepartureDates;
    }

    public void initTempArray() {
        iteratedFlightIds = new ArrayList<>();
    }
}
