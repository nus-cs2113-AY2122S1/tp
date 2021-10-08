package seedu.duke.task.type;

import java.util.Date;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;

public class Event extends Task {

    private Date endDate;

    public Event(String description, Date date) {
        super(description, date);
    }

    public Event(String description, Date startDate, Date endDate) {
        super(description, startDate);
        setEndDate(endDate);
    }

    public Event(String description, Date date, RecurrenceEnum recurranceEnum) {
        super(description, date, recurranceEnum);
    }

    public Event(String description, Date startDate, Date endDate, RecurrenceEnum recurranceEnum) {
        super(description, startDate, recurranceEnum);
        setEndDate(endDate);
    }

    public Event(String description, Date date, PriorityEnum priorityEnum) {
        super(description, date, priorityEnum);
    }

    public Event(String description, Date startDate, Date endDate, PriorityEnum priorityEnum) {
        super(description, startDate, priorityEnum);
        setEndDate(endDate);
    }

    public Event(String description, Date date, RecurrenceEnum recurranceEnum, PriorityEnum priorityEnum) {
        super(description, date, recurranceEnum, priorityEnum);
    }

    public Event(String description, Date startDate, Date endDate,
                 RecurrenceEnum recurranceEnum, PriorityEnum priorityEnum) {
        super(description, startDate, recurranceEnum, priorityEnum);
        setEndDate(endDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
