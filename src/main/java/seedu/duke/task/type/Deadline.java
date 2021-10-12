package seedu.duke.task.type;

import java.util.Date;
import seedu.duke.parser.UtilityParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;

public class Deadline extends Task {

    private static final String DEADLINE_DATE_DESCRIPTION_REGEX = " (dueDate: %s)";

    private static final String DUE_DATE_NOT_NULL_ASSERTION = "dueDate for Deadline cannot be null.";

    private Date dueDate;

    public Deadline(String description, Date dueDate) {
        super(description);
        setDueDate(dueDate);
    }

    public Deadline(String description, Date dueDate, PriorityEnum priority) {
        this(description, dueDate);
        setPriority(priority);
    }

    public Deadline(String description, Date dueDate, RecurrenceEnum recurrence) {
        this(description, dueDate);
        setRecurrence(recurrence);
    }

    public Deadline(String description, Date dueDate, PriorityEnum priority, RecurrenceEnum recurrence) {
        this(description, dueDate);
        setPriority(priority);
        setRecurrence(recurrence);
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        assert dueDate != null : DUE_DATE_NOT_NULL_ASSERTION;
        this.dueDate = dueDate;
    }

    @Override
    public String getTaskEntryDescription() {
        return super.getTaskEntryDescription()
                + String.format(DEADLINE_DATE_DESCRIPTION_REGEX, UtilityParser.getDateAsString(getDueDate()));
    }

}
