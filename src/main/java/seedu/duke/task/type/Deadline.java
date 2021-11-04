package seedu.duke.task.type;


import java.time.LocalDateTime;
import java.util.Map;
import seedu.duke.command.flags.DeadlineFlag;
import seedu.duke.exception.ParseDateFailedException;
import seedu.duke.parser.DateParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.reminder.Reminder;

public class Deadline extends Task {

    private static final TypeEnum TASK_TYPE = TypeEnum.DEADLINE;

    private static final String DEADLINE_ICON = "[D]";
    private static final String DEADLINE_DATE_DESCRIPTION_REGEX = " (dueDate: %s)";

    private static final String DUE_DATE_NOT_NULL_ASSERTION = "dueDate for Deadline cannot be null.";

    private LocalDateTime dueDate;

    public Deadline(String description, LocalDateTime dueDate) {
        super(description);
        setDueDate(dueDate);
    }

    public Deadline(String description, LocalDateTime dueDate, PriorityEnum priority) {
        this(description, dueDate);
        setPriority(priority);
    }

    public Deadline(String description, LocalDateTime dueDate, RecurrenceEnum recurrence) {
        this(description, dueDate);
        setRecurrence(recurrence);
    }

    public Deadline(String description, LocalDateTime dueDate, PriorityEnum priority, RecurrenceEnum recurrence) {
        this(description, dueDate);
        setPriority(priority);
        setRecurrence(recurrence);
    }

    public TypeEnum getTaskType() {
        return this.TASK_TYPE;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        assert dueDate != null : DUE_DATE_NOT_NULL_ASSERTION;
        this.dueDate = dueDate;
        setReminder(new Reminder(dueDate));
    }

    @Override
    public String getTaskEntryDescription() {
        return DEADLINE_ICON + " " + super.getTaskEntryDescription()
            + String.format(DEADLINE_DATE_DESCRIPTION_REGEX, DateParser.dateToString(getDueDate()));
    }

    //@@author SeanRobertDH
    /**
     * Edits the {@link #dueDate} if {@link seedu.duke.command.flags.DeadlineFlag#DUE_DATE}
     *     is a key in <code>arguments</code>.
     *
     * @param arguments <code>Map&lt;String, String&gt;</code> of flags to values
     *     that should be edited in {@link seedu.duke.task.type.Deadline}.
     * @throws seedu.duke.exception.ParseDateFailedException if unable to parse <code>due</code>.
     */
    @Override
    protected void taskEdit(Map<String, String> arguments) throws ParseDateFailedException {
        if (arguments.containsKey(DeadlineFlag.DUE_DATE)) {
            String due = arguments.get(DeadlineFlag.DUE_DATE);
            setDueDate(DateParser.stringToDate(due));
        }
    }

    //@@author SeanRobertDH
    /**
     * Updates the {@link #dueDate} if {@link seedu.duke.task.type.Deadline} has a recurrence to the latest date.
     */
    @Override
    public void refreshDate() {
        LocalDateTime newDueDate = getRecurrence().getNextRecurredDate(getDueDate());
        setDueDate(newDueDate);
    }

    //@@author SeanRobertDH
    /**
     * Returns {@link #dueDate}.
     */
    @Override
    public LocalDateTime getListDate() {
        return getDueDate();
    }
}
