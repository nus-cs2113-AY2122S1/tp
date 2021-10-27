package seedu.duke.data;

public class Flight {
    private final String id;
    private final String fromDestination;
    private final String toDestination;
    private final String fromDate;
    private final String toDate;

    public Flight(String[] values) {
        id = values[0];
        toDestination = values[1];
        fromDestination = values[2];
        toDate = values[3];
        fromDate = values[4];
    }

    public Flight(String flightId, String to, String from, String toDate, String fromDate) {
        id = flightId;
        toDestination = to;
        fromDestination = from;
        this.toDate = toDate;
        this.fromDate = fromDate;
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
