package seedu.duke.items;

import java.time.LocalDateTime;

public class Task extends Item {

    private final LocalDateTime deadline;
    private String description;

    public Task(String title, LocalDateTime deadline, String description) {
        super(title);
        this.deadline = deadline;
        this.description = description;
    }

    public static String getItemType() {
        return "task";
    }

    @Override
    public LocalDateTime getDateValue() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }
}
