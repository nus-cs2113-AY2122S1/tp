package seedu.duke.items;

import java.time.LocalDateTime;

public class Task extends Item {

    private LocalDateTime deadline;

    public static final String TASK_DATA_ARGS_DELIMITER = "\\s*\\|\\s*";

    public Task(String title, String description, LocalDateTime deadline) {
        super("task", title, description, deadline);
    }
}
