package seedu.duke.task.type;

import java.util.Date;
import seedu.duke.parser.UtilityParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;

public class Todo extends Task {

    private static final String TODO_DATE_DESCRIPTION_REGEX = " (doOn: %s)";

    private Date doOnDate;

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
