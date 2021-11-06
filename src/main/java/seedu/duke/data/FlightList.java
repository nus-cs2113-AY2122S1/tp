package seedu.duke.data;

import seedu.duke.TourPlannerException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * List of flights.
 */
public class FlightList {
    private static final String FLIGHT_NOT_FOUND_MESSAGE = "ERROR: Flight cannot be found. "
            + "Please try another flight ID";

    private final ArrayList<Flight> flights;
    private ArrayList<String> flightIds;
    private ArrayList<String> flightReturnDates;
    private ArrayList<String> flightDepartureDates;
    private ArrayList<String> iteratedFlightIds;
    private int flightCount = 0;

    /**
     * Comparator for extension of Collections.sort() which does not include date-time sort functionality.
     * Overrides compare method within the Comparator to compare between date-times.
     *
     * @see Comparator
     */
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

    /**
     * Getter for flight list.
     *
     * @return the list of flights
     */
    public ArrayList<Flight> getFlights() {
        return flights;
    }

    /**
     * Class constructor for FlightList.
     */
    public FlightList() {
        flights = new ArrayList<>();
        flightIds = new ArrayList<>();
        flightReturnDates = new ArrayList<>();
        flightDepartureDates = new ArrayList<>();
        flightCount = 0;
    }

    /**
     * Main method for adding a flight.
     * Add all information of different fields into their respective ArrayLists.
     *
     * @param flight the flight to be added
     */
    public void add(Flight flight) {
        flights.add(flight);
        flightIds.add(flight.getId());
        flightReturnDates.add(flight.getReturnDate());
        flightDepartureDates.add(flight.getDepartDate());
        flightCount++;
    }

    /**
     * Main method for cutting a flight.
     * Removes all information of different fields from their respective ArrayLists.
     *
     * @param flight the flight to be added
     */
    public void cut(Flight flight) {
        flights.remove(flight);
        flightIds.remove(flight.getId());
        flightReturnDates.remove(flight.getReturnDate());
        flightDepartureDates.remove(flight.getDepartDate());
        flightCount--;
    }

    /**
     * Getter for number of flights in the flight list.
     *
     * @return the number of flights in flight list.
     */
    public int getFlightCount() {
        return flightCount;
    }

    /**
     * Getter for flight object in the flight list, corresponding to the index given.
     *
     * @param index the index of the specific flight in the flight list
     * @return the flight object corresponding to the index
     */
    public Flight getFlightByIndex(int index) {
        return flights.get(index);
    }

    /**
     * Getter for flight object in the flight list, corresponding to the flight ID given.
     *
     * @param flightId the given flight ID, unique to each set of flights
     * @return the flight object, specified by the flight ID
     * @throws TourPlannerException if the flight with the given flight ID is not found in the database
     */
    public Flight getFlightById(String flightId) throws TourPlannerException {
        for (int i = 0; i < flightCount; i++) {
            if (flights.get(i).getId().equals(flightId)) {
                return flights.get(i);
            }
        }
        throw new TourPlannerException(FLIGHT_NOT_FOUND_MESSAGE);
    }

    /**
     * Getter for flight object in the flight list, corresponding to the flight return date given.
     *
     * @param flightReturnDate the date-time of the return flight, in String
     * @return the flight object, specified by the return flight's date-time
     * @throws TourPlannerException if the flight with the given return flight's date-time is not found in the database
     */
    public Flight getFlightByReturnDate(String flightReturnDate) throws TourPlannerException {
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

    /**
     * Getter for flight object in the flight list, corresponding to the flight departure date given.
     *
     * @param flightDepartDate the date-time of the departure flight, in String
     * @return the flight object, specified by the departure flight's date-time
     * @throws TourPlannerException if the flight with the given departure flight's date-time
     *                              is not found in the database
     */
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

    /**
     * Getter for the sorted list of flight IDs.
     * Sorts the flight IDs by the natural ordering of String, ignoring case sensitivities.
     *
     * @return the list of sorted flight IDs, by natural(alphabetical, numerical) order
     * @see Collections#sort(List, Comparator)
     */
    public ArrayList<String> getSortedFlightIds() {
        Collections.sort(flightIds, String.CASE_INSENSITIVE_ORDER);
        return flightIds;
    }

    /**
     * Getter for the sorted list of flight return date-times.
     * Sorts using the dateTimeStringComparator in FlightList.
     *
     * @return the list of sorted return flight date-times, as specified in dateTimeStringComparator
     * @see FlightList#dateTimeStringComparator
     * @see Collections#sort(List)
     */
    public ArrayList<String> getSortedReturnDates() {
        flightReturnDates.sort(dateTimeStringComparator);
        return flightReturnDates;
    }

    /**
     * Getter for the sorted list of flight departure date-times.
     * Sorts using the dateTimeStringComparator in FlightList.
     *
     * @return the list of sorted departure flight date-times, as specified in dateTimeStringComparator
     * @see FlightList#dateTimeStringComparator
     * @see Collections#sort(List)
     */
    public ArrayList<String> getSortedDepartDates() {
        flightDepartureDates.sort(dateTimeStringComparator);
        return flightDepartureDates;
    }

    /**
     * Creates a new temporary array each time the function is called.
     * The flight IDs that have been iterated by Ui in the sort command will be added into FlightList's
     * temporary array to prevent duplicates.
     */
    public void initTempArray() {
        iteratedFlightIds = new ArrayList<>();
    }
}
