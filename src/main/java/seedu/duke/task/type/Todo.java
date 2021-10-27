package seedu.duke.task.type;

import java.time.LocalDateTime;
import seedu.duke.parser.DateParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.reminder.Reminder;

public class Todo extends Task {

    private static final TypeEnum TASK_TYPE = TypeEnum.TODO;

    private static final String TODO_ICON = "[T]";
    private static final String TODO_DATE_DESCRIPTION_REGEX = " (doOn: %s)";

    private LocalDateTime doOnDate;

    private Reminder reminder;

    public Todo(String description) {
        super(description);
        setDoOnDate(null);
    }

    public Todo(String description, PriorityEnum priority) {
        this(description);
        setPriority(priority);
    }

    public Todo(String description, LocalDateTime doOnDate) {
        this(description);
        setDoOnDate(doOnDate);
    }

    public Todo(String description, PriorityEnum priority, LocalDateTime doOnDate) {
        this(description);
        setPriority(priority);
        setDoOnDate(doOnDate);
    }

    public Todo(String description, LocalDateTime doOnDate, RecurrenceEnum recurrence) {
        this(description);
        setDoOnDate(doOnDate);
        setRecurrence(recurrence);
    }

    public Todo(String description, PriorityEnum priority, LocalDateTime doOnDate, RecurrenceEnum recurrence) {
        this(description);
        setPriority(priority);
        setDoOnDate(doOnDate);
        setRecurrence(recurrence);
    }

    public TypeEnum getTaskType() {
        return this.TASK_TYPE;
    }

    public LocalDateTime getDoOnDate() {
        return doOnDate;
    }

    public void setDoOnDate(LocalDateTime doOnDate) {
        this.doOnDate = doOnDate;
        if (doOnDate != null) {
            reminder = new Reminder(doOnDate);
        }
    }

    public String getReminder(LocalDateTime now) {
        return reminder.getRecurrenceMessage(now, getTaskEntryDescription(), getRecurrence());
    }

    @Override
    public boolean needReminder() {
        return (reminder != null);
    }

    @Override
    public String getTaskEntryDescription() {
        String description = TODO_ICON + " " + super.getTaskEntryDescription();
        if (getDoOnDate() != null) {
            description += String.format(TODO_DATE_DESCRIPTION_REGEX, DateParser.dateToString(getDoOnDate()));
        }
        return description;
    }
}
