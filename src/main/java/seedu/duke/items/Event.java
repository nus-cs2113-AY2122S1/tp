package seedu.duke.items;

import java.time.LocalDateTime;

public class Event extends Item {
    public static String getItemType() {
        return "event";
    }

    public LocalDateTime getDateValue() {
        return null;
    }
}
