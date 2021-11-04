package seedu.duke.task.type;

import java.time.LocalDateTime;
import java.util.Map;
import seedu.duke.command.flags.TodoFlag;
import seedu.duke.exception.ParseDateFailedException;
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

    public Todo(String description) {
        super(description);
        setDoOnDate(DateParser.roundUpHour(LocalDateTime.now()));
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
        setReminder(new Reminder(doOnDate));
    }

    @Override
    public String getTaskEntryDescription() {
        String description = TODO_ICON + " " + super.getTaskEntryDescription();
        if (getDoOnDate() != null) {
            description += String.format(TODO_DATE_DESCRIPTION_REGEX, DateParser.dateToString(getDoOnDate()));
        }
        return description;
    }

    //@@author SeanRobertDH
    /**
     * Edits the {@link #doOnDate} if {@link seedu.duke.command.flags.TodoFlag#DO_ON_DATE}
     *     is a key in <code>arguments</code>.
     *
     * @param arguments <code>Map&lt;String, String&gt;</code> of flags to values
     *     that should be edited in {@link seedu.duke.task.type.Todo}.
     * @throws seedu.duke.exception.ParseDateFailedException if unable to parse <code>doOn</code>.
     */
    @Override
    protected void taskEdit(Map<String, String> arguments) throws ParseDateFailedException {
        if (arguments.containsKey(TodoFlag.DO_ON_DATE)) {
            String doOn = arguments.get(TodoFlag.DO_ON_DATE);
            setDoOnDate(DateParser.stringToDate(doOn));
        }
    }

    //@@author SeanRobertDH
    /**
     * Updates the {@link #doOnDate} if {@link seedu.duke.task.type.Todo} has a recurrence to the latest date.
     */
    @Override
    public void refreshDate() {
        LocalDateTime newDoOnDate = getRecurrence().getNextRecurredDate(getDoOnDate());
        setDoOnDate(newDoOnDate);
    }

    //@@author SeanRobertDH
    /**
     * Returns {@link #doOnDate}.
     */
    @Override
    public LocalDateTime getListDate() {
        return getDoOnDate();
    }
}
