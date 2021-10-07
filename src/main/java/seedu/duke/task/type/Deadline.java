package seedu.duke.task.type;

import java.util.Date;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;

public class Deadline extends Task {

    public Deadline(String description, Date dueDate) {
        super(description, dueDate);
    }

    public Deadline(String description, Date dueDate, RecurrenceEnum recurranceEnum) {
        super(description, dueDate, recurranceEnum);
    }

    public Deadline(String description, Date dueDate, PriorityEnum priorityEnum) {
        super(description, dueDate, priorityEnum);
    }

    public Deadline(String description, Date dueDate, RecurrenceEnum recurranceEnum, PriorityEnum priorityEnum) {
        super(description, dueDate, recurranceEnum, priorityEnum);
    }
}
