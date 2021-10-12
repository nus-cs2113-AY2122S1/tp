package seedu.duke.task.type;

import java.time.LocalDateTime;
import java.util.Date;
import seedu.duke.parser.UtilityParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.reminder.Reminder;

public class Todo extends Task {

    private static final String TODO_DATE_DESCRIPTION_REGEX = " (doOn: %s)";

    private Date doOnDate;

    private Reminder reminder;

    public Todo(String description) {
        super(description);
        setDoOnDate(null);
    }

    public Todo(String description, PriorityEnum priority) {
        this(description);
        setPriority(priority);
    }

    public Todo(String description, Date doOnDate) {
        this(description);
        setDoOnDate(doOnDate);
    }

    public Todo(String description, PriorityEnum priority, Date doOnDate) {
        this(description);
        setPriority(priority);
        setDoOnDate(doOnDate);
    }
    
    public Todo(String description, Date doOnDate, RecurrenceEnum recurrence) {
        this(description);
        setDoOnDate(doOnDate);
        setRecurrence(recurrence);
    }

    public Todo(String description, PriorityEnum priority, Date doOnDate, RecurrenceEnum recurrence) {
        this(description);
        setPriority(priority);
        setDoOnDate(doOnDate);
        setRecurrence(recurrence);
    }

    public Date getDoOnDate() {
        return doOnDate;
    }

    public void setDoOnDate(Date doOnDate) {
        this.doOnDate = doOnDate;
        if (doOnDate != null) {
            reminder = new Reminder(doOnDate);
        }
    }


    public String getReminder(LocalDateTime now) {
        return reminder.getRecurrenceMessage(now, getTaskEntryDescription(), recurrence);
    }
    @Overrride
    public boolean needReminder() {
        return (reminder != null);
    }

    @Override
    public String getTaskEntryDescription() {
        String description = super.getTaskEntryDescription();
        if (getDoOnDate() != null) {
            description += String.format(TODO_DATE_DESCRIPTION_REGEX, UtilityParser.getDateAsString(getDoOnDate()));
        }
        return description;
    }
}
