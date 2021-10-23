package seedu.duke;

public class Flight {
    private final String id;
    private final String fromDestination;
    private final String toDestination;
    private final String fromDate;
    private final String toDate;

    public Flight(String[] values) {
        id = values[0];
        fromDestination = values[1];
        toDestination = values[2];
        fromDate = values[3];
        toDate = values[4];
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
        return "Flight ID: " + id + System.lineSeparator()
                + "Departure Flight: " + fromDestination + ", " + fromDate + System.lineSeparator()
                + "Return Flight: " + toDestination + ", " + toDate;
    }
}
