package seedu.duke.items;

import java.time.LocalDateTime;

public class Event extends Item {

    public static final String EVENT_DATA_ARGS_DELIMITER = "\\s*\\|\\s*";

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
