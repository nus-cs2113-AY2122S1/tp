package seedu.duke.data;

/**
 * Represents a client under the Tour agency.
 */
public class Client {
    private final String id;
    private final String name;
    private final String contactNum;
    private final String email;

    /**
     * Client object constructor, that inputs the client's information obtained from Add command.
     *
     * @param values the array of values of the client, ordered in this manner:
     *               name, contact number, flight, accommodation, tour
     */
    public Client(String[] values) {
        id = values[0];
        name = values[1];
        contactNum = values[2];
        email = values[3];
    }

    public String getId() {
        return id;
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

    public String getEmail() {
        return email;
    }

    /**
     * Formats client's information as a string that is viewable as an indexed list item.
     *
     * @return the formatted string containing client's information
     */
    @Override
    public String toString() {

        return "Client ID: " + id + System.lineSeparator()
                + "Name: " + name + System.lineSeparator()
                + "Contact Number: " + contactNum + System.lineSeparator()
                + "Email: " + email;
    }
}
