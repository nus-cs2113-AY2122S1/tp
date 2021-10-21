package seedu.duke;

public class Flight {
    private final String id;
    private final String toDestination;
    private final String fromDestination;
    private final String toDate;
    private final String fromDate;

    public Flight(String[] values) {
        id = values[0];
        toDestination = values[1];
        fromDestination = values[2];
        toDate = values[3];
        fromDate = values[4];
    }

    public String getId() {
        return id;
    }

    public String getToDestination() {
        return toDestination;
    }

    public String getFromDestination() {
        return fromDestination;
    }

    public String getToDate() {
        return toDate;
    }

    public String getFromDate() {
        return fromDate;
    }

    @Override
    public String toString() {

        return "Flight's id: " + id + System.lineSeparator()
                + "Flight's to destination: " + toDestination + System.lineSeparator()
                + "Client's from destination: " + fromDestination + System.lineSeparator()
                + "Client's to date: " + toDate + System.lineSeparator()
                + "Client's from date: " + fromDate;
    }
}
