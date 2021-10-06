package seedu.duke.items;

import java.time.LocalDateTime;

public class Item {

    protected String title;

    public Item(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public static String getItemType() {
        return null;
    }

    public LocalDateTime getDateValue() {
        return null;
    }
}
