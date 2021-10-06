package seedu.duke.items;

import java.time.LocalDateTime;

public class Event extends Item {

    private final LocalDateTime dateAndTime;
    private String venue;
    private String description;
    private double budget;

    // for small testing purposes
    public Event(String title, LocalDateTime dateAndTime) {
        super(title);
        this.dateAndTime = dateAndTime;
    }

    public Event(String title, LocalDateTime dateAndTime, String venue, String description, double budget) {
        super(title);
        this.dateAndTime = dateAndTime;
        this.venue = venue;
        this.description = description;
        this.budget = budget;
    }

    public static String getItemType() {
        return "event";
    }

    @Override
    public LocalDateTime getDateValue() {
        return dateAndTime;
    }

    public String getVenue() {
        return venue;
    }

    public String getDescription() {
        return description;
    }

    public double getBudget() {
        return budget;
    }
}
