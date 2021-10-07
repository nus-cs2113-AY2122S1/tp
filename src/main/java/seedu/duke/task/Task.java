package seedu.duke.task;

import java.util.Date;

public abstract class Task {

    static final PriorityEnum DEFAULT_PRIORITY = PriorityEnum.MEDIUM;
    static final RecurrenceEnum DEFAULT_RECURRENCE = RecurrenceEnum.NONE;

    String description;
    PriorityEnum priorityEnum;
    RecurrenceEnum recurranceEnum;
    Date date;

    protected Task(String description) {
        this.description = description;
        setPriorityEnum(DEFAULT_PRIORITY);
        setRecurranceEnum(DEFAULT_RECURRENCE);
    }

    protected Task(String description, Date date) {
        this(description);
        setDate(date);
    }

    protected Task(String description, Date date, RecurrenceEnum recurranceEnum) {
        this(description, date);
        setRecurranceEnum(recurranceEnum);
    }

    protected Task(String description, PriorityEnum priorityEnum) {
        this(description);
        setPriorityEnum(priorityEnum);
    }

    protected Task(String description, Date date, PriorityEnum priorityEnum) {
        this(description, date);
        setPriorityEnum(priorityEnum);
    }

    protected Task(String description, Date date, RecurrenceEnum recurranceEnum, PriorityEnum priorityEnum) {
        this(description, date, priorityEnum);
        setRecurranceEnum(recurranceEnum);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PriorityEnum getPriorityEnum() {
        return priorityEnum;
    }

    public void setPriorityEnum(PriorityEnum priorityEnum) {
        this.priorityEnum = priorityEnum;
    }

    public RecurrenceEnum getRecurranceEnum() {
        return recurranceEnum;
    }

    public void setRecurranceEnum(RecurrenceEnum recurranceEnum) {
        this.recurranceEnum = recurranceEnum;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
