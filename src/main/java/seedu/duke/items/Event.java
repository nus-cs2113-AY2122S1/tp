package seedu.duke.items;

import java.time.LocalDateTime;

public class Event extends Item {

    private String dateTime;
    private String venue;
    private double budget;

    public static final String EVENT_DATA_ARGS_DELIMITER = "\\s*\\|\\s*";

    public Event(String title, String description, String dateTime, String venue, int budget) {
        super("event", title, description);
        this.dateTime = dateTime;
        this.venue = venue;
        this.budget = budget;
    }

    public String getDateValue() {
        return dateTime;
    }

    public String getVenue() {
        return venue;
    }

    public double getBudget() {
        return budget;
    }
}
