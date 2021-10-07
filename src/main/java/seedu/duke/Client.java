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
}
