package seedu.duke.items;

import java.time.LocalDateTime;

public abstract class Item {

    protected String type;
    protected String title;
    protected String description;
    protected LocalDateTime dateTime;
    protected boolean isDone;

    public Item(String type, String title, String description, LocalDateTime dateTime) {
        this.type = type;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.isDone = false;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getItemType() {
        return type;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
