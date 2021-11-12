package seedu.duke.data;

/**
 * Represents a flight under the Tour agency.
 */
public class Flight {
    private final String id;
    private final String departDestination;
    private final String returnDestination;
    private final String departDate;
    private final String returnDate;

    /**
     * Flight object constructor, that inputs the flight's information obtained from Add command.
     *
     * @param values the array of values of the flight, ordered in this manner:
     *               flight ID, departure destination, return destination, departure date-time, return date-time
     */
    public Flight(String[] values) {
        id = values[0];
        departDestination = values[1];
        returnDestination = values[2];
        departDate = values[3];
        returnDate = values[4];
    }

    /**
     * Getter for id value in Flight object.
     *
     * @return flight's id
     */
    public String getId() {
        return id;
    }

    /**
     * Getter for return destination in Flight object.
     *
     * @return flight's return destination
     */
    public String getReturnDestination() {
        return returnDestination;
    }

    /**
     * Getter for departure destination in Flight object.
     *
     * @return flight's departure destination
     */
    public String getDepartDestination() {
        return departDestination;
    }

    /**
     * Getter for return date-time in Flight's object.
     *
     * @return flight's return date-time
     */
    public String getReturnDate() {
        return returnDate;
    }

    /**
     * Getter for departure date-time in Flight's object.
     *
     * @return flight's departure date-time
     */
    public String getDepartDate() {
        return departDate;
    }

    /**
     * Formats flight's information as a string that is viewable as an indexed list item.
     *
     * @return the formatted string containing flight's information
     */
    @Override
    public String toString() {
        return "Flight ID: " + id + "\n"
                + "Departure Flight: " + departDestination + ", " + departDate + "\n"
                + "Return Flight: " + returnDestination + ", " + returnDate;
    }
}
