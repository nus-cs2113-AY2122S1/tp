package seedu.duke;

public class Client {
    private final String name;
    private final String contactNum;
    private final String flight;
    private final String accomms;
    private final String tour;

    public Client(String[] values) {
        name = values[0];
        contactNum = values[1];
        flight = values[2];
        accomms = values[3];
        tour = values[4];
    }

    @Override
    public String toString() {
        return "Client's name: " + name + System.lineSeparator()
                + "Client's contactNum: " + contactNum + System.lineSeparator()
                + "Client's flight number: " + flight + System.lineSeparator()
                + "Client's accomms: " + accomms + System.lineSeparator()
                + "Client's tour: " + tour + System.lineSeparator();
    }
}
