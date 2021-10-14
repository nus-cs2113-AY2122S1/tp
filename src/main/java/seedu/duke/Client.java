package seedu.duke;

/**
 * Represents a client under the Tour agency.
 */
public class Client {
    private final String name;
    private final String contactNum;
    private final String flight;
    private final String accomms;
    private final String tour;

    /**
     * Client object constructor, that inputs the client's information obtained from Add command.
     *
     * @param values the array of values of the client, ordered in this manner:
     *               name, contact number, flight, accommodation, tour
     */
    public Client(String[] values) {
        name = values[0];
        contactNum = values[1];
        flight = values[2];
        accomms = values[3];
        tour = values[4];
    }

    /**
     * Getter for name value in Client object.
     *
     * @return client's name
     */
    public String getName() {
        return name;
    }

    /**
     * Getter for contact number value in Client object.
     *
     * @return client's contact number
     */
    public String getContactNum() {
        return contactNum;
    }

    /**
     * Getter for flight number value in Client object.
     *
     * @return client's flight number
     */
    public String getFlight() {
        return flight;
    }

    /**
     * Getter for accommodations value in Client object.
     *
     * @return client's accommodation
     */
    public String getAccomms() {
        return accomms;
    }

    /**
     * Getter for tour value in Client object.
     *
     * @return client's tour package
     */
    public String getTour() {
        return tour;
    }

    /**
     * Formats client's information as a string that is viewable as an indexed list item.
     *
     * @return the formatted string containing client's information
     */
    @Override
    public String toString() {

        return "Client's name: " + name + System.lineSeparator()
                + "Client's contactNum: " + contactNum + System.lineSeparator()
                + "Client's flight number: " + flight + System.lineSeparator()
                + "Client's accomms: " + accomms + System.lineSeparator()
                + "Client's tour: " + tour + System.lineSeparator();
    }
}
