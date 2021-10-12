package seedu.duke.task.factory.arguments;

import java.util.Date;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;

public class EventArguments {
    private final String description;
    private final Date startDate;
    private final Date endDate;
    private final PriorityEnum priorityEnum;
    private final RecurrenceEnum recurrenceEnum;

    public EventArguments(String description,
            Date startDate, Date endDate, PriorityEnum priorityEnum, RecurrenceEnum recurrenceEnum) {
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.priorityEnum = priorityEnum;
        this.recurrenceEnum = recurrenceEnum;
    }

    public String getDescription() {
        return description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public PriorityEnum getPriority() {
        return priorityEnum;
    }

    public RecurrenceEnum getRecurrence() {
        return recurrenceEnum;
    }
}
