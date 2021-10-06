package seedu.duke.items;

import java.time.LocalDateTime;

public class Event extends Item {

    public Event(String eventTitle, String eventDateTime, String eventVenue, String eventDescription, String eventBudget) {
        super();
    }

    public static String getItemType() {
        return "event";
    }

    public LocalDateTime getDateValue() {
        return null;
    }
}
