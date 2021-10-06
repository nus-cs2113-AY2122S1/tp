package seedu.duke.items;

import java.time.LocalDateTime;

public class Task extends Item {

    public Task(String taskTitle, String taskDeadline, String taskDescription) {
        super();
    }

    public static String getItemType() {
        return "task";
    }

    public LocalDateTime getDateValue() {
        return null;
    }
}
