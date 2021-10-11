package seedu.duke.task.factory.arguments;

import java.util.Date;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;

public class TodoArguments {
    private final String description;
    private final PriorityEnum priorityEnum;
    private final Date doOnDate;
    private final RecurrenceEnum recurrenceEnum;

    public TodoArguments(String description, PriorityEnum priorityEnum, Date doOnDate, RecurrenceEnum recurrenceEnum) {
        this.description = description;
        this.priorityEnum = priorityEnum;
        this.doOnDate = doOnDate;
        this.recurrenceEnum = recurrenceEnum;
    }

    public String getDescription() {
        return description;
    }

    public PriorityEnum getPriority() {
        return priorityEnum;
    }

    public Date getDoOnDate() {
        return doOnDate;
    }

    public RecurrenceEnum getRecurrence() {
        return recurrenceEnum;
    }


}
