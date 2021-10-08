package seedu.duke.task.type;

import java.util.Date;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;

public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    public Todo(String description, Date date) {
        super(description, date);
    }

    public Todo(String description, Date date, RecurrenceEnum recurranceEnum) {
        super(description, date, recurranceEnum);
    }

    public Todo(String description, PriorityEnum priorityEnum) {
        super(description, priorityEnum);
    }

    public Todo(String description, Date date, PriorityEnum priorityEnum) {
        super(description, date, priorityEnum);
    }

    public Todo(String description, Date date, RecurrenceEnum recurranceEnum, PriorityEnum priorityEnum) {
        super(description, date, recurranceEnum, priorityEnum);
    }
}
