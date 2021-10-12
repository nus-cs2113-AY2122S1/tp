package seedu.duke.items;

import seedu.duke.Parser;

import java.time.LocalDateTime;

public class Event extends Item {

    private LocalDateTime dateTime;
    private String venue;
    private double budget;

    public static final String EVENT_DATA_ARGS_DELIMITER = "\\s*\\|\\s*";

    public Event(String title, String description, String date, String venue, double budget) {
        super("event", title, description);
        this.dateTime = Parser.convertDateTime(date);
        this.venue = venue;
        this.budget = budget;
    }

    public LocalDateTime getDateValue() {
        return dateTime;
    }

    public String getItemType() {
        return "event";
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setVenue(String venue) {
        this.venue = venue;
    }

    public void setBudget(Double budget) {
        this.budget = budget;
    }

    public String getVenue() {
        return venue;
    }

    public double getBudget() {
        return budget;
    }

    @Override
    public String toString() {
        return String.format("[E] %s (at: %s)", this.getTitle(), Parser.convertDateTime(this.getDateValue()));
    }
}
