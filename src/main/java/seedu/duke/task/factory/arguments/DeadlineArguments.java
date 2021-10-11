package seedu.duke.task.factory.arguments;

import java.util.Date;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;

public class DeadlineArguments {
    private final String description;
    private final Date dueDate;
    private final PriorityEnum priorityEnum;
    private final RecurrenceEnum recurrenceEnum;

    public DeadlineArguments(String description,
                             Date dueDate, PriorityEnum priorityEnum, RecurrenceEnum recurrenceEnum) {
        this.description = description;
        this.dueDate = dueDate;
        this.priorityEnum = priorityEnum;
        this.recurrenceEnum = recurrenceEnum;
    }

    public String getDescription() {
        return description;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public PriorityEnum getPriority() {
        return priorityEnum;
    }

    public RecurrenceEnum getRecurrence() {
        return recurrenceEnum;
    }
}
