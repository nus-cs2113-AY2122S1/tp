package seedu.duke.items;

import java.time.LocalDateTime;

public class Task extends Item {

    private String deadline;

    public static final String TASK_DATA_ARGS_DELIMITER = "\\s*\\|\\s*";

    public Task(String title, String description, String deadline) {
        super("task", title, description);
        this.deadline = deadline;
    }

    public String getDeadline() {
        return deadline;
    }

    public String getItemType() {
        return "task";
    }

    @Override
    public String getDateValue() {
        return deadline;
    }
}
