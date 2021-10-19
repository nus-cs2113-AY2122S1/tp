package seedu.duke.task.type;

import java.time.LocalDateTime;
import java.util.Date;
import seedu.duke.parser.UtilityParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.reminder.Reminder;

public class Event extends Task {

    private final TypeEnum taskType = TypeEnum.EVENT;

    private static final String DEADLINE_DATE_DESCRIPTION_REGEX = " (startDate: %s - endDate: %s)";

    private static final String START_DATE_NOT_NULL_ASSERTION = "startDate for Event cannot be null";
    private static final String END_DATE_NOT_NULL_ASSERTION = "endDate for Event cannot be null";
    private static final String START_DATE_BEFORE_END_DATE_ASSERTION = "Start date must be before end date!";

    private Reminder reminder;
    private Date startDate;
    private Date endDate;

    public Event(String description, Date startDate, Date endDate) {
        super(description);
        setStartDate(startDate);
        setEndDate(endDate);
    }

    public Event(String description, Date startDate, Date endDate, PriorityEnum priority) {
        this(description, startDate, endDate);
        setPriority(priority);
    }

    public Event(String description, Date startDate, Date endDate, RecurrenceEnum recurrence) {
        this(description, startDate, endDate);
        setRecurrence(recurrence);
    }

    public Event(String description, Date startDate, Date endDate, PriorityEnum priority, RecurrenceEnum recurrence) {
        this(description, startDate, endDate);
        setPriority(priority);
        setRecurrence(recurrence);
    }

    public TypeEnum getTaskType() {
        return this.taskType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        assert startDate != null : START_DATE_NOT_NULL_ASSERTION;
        if (endDate != null) {
            assert startDate.before(endDate) : START_DATE_BEFORE_END_DATE_ASSERTION;
        }
        this.startDate = startDate;
        reminder = new Reminder(startDate);
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        assert endDate != null : END_DATE_NOT_NULL_ASSERTION;
        if (startDate != null) {
            assert startDate.before(endDate) : START_DATE_BEFORE_END_DATE_ASSERTION;
        }
        this.endDate = endDate;
    }
  
    @Override
    public boolean needReminder() {
        return (reminder != null);
    }

    @Override
    public String getTaskEntryDescription() {
        return super.getTaskEntryDescription()
            + String.format(DEADLINE_DATE_DESCRIPTION_REGEX,
            UtilityParser.getDateAsString(getStartDate()), UtilityParser.getDateAsString(getEndDate()));
    }

    public String getReminder(LocalDateTime now) {
        return reminder.getRecurrenceMessage(now, getTaskEntryDescription(), getRecurrence());
    }
}
