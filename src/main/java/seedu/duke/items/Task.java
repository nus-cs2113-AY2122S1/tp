package seedu.duke.items;

import seedu.duke.Parser;

import java.time.LocalDateTime;

public class Task extends Item {

    private LocalDateTime deadline;

    public static final String TASK_DATA_ARGS_DELIMITER = "\\s*\\|\\s*";

    public Task(String title, String description, String deadline) {
        super("task", title, description);
        this.deadline = Parser.convertDateTime(deadline);
    }

    public String getDeadline() {
        return Parser.convertDateTime(deadline);
    }

    public String getItemType() {
        return "task";
    }

    public LocalDateTime getDateValue() {
        return deadline;
    }
}
