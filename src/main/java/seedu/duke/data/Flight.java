package seedu.duke.data;

public class Flight {
    private final String id;
    private final String departDestination;
    private final String returnDestination;
    private final String departDate;
    private final String returnDate;

    public Flight(String[] values) {
        id = values[0];
        departDestination = values[1];
        returnDestination = values[2];
        departDate = values[3];
        returnDate = values[4];
    }

    public String getId() {
        return id;
    }

    public String getReturnDestination() {
        return returnDestination;
    }

    public String getDepartDestination() {
        return departDestination;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getDepartDate() {
        return departDate;
    }

    @Override
    public String toString() {
        return "Flight ID: " + id + System.lineSeparator()
                + "Departure Flight: " + departDestination + ", " + departDate + System.lineSeparator()
                + "Return Flight: " + returnDestination + ", " + returnDate;
    }
}
