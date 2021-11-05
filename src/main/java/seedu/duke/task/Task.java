package seedu.duke.task;

import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import seedu.duke.command.Command;
import seedu.duke.command.flags.TaskFlag;
import seedu.duke.exception.InvalidFlagsException;
import seedu.duke.task.reminder.Reminder;
import seedu.duke.task.reminder.ReminderInformation;
import seedu.duke.exception.InvalidPriorityException;
import seedu.duke.exception.InvalidRecurrenceException;
import seedu.duke.exception.ParseDateFailedException;
import seedu.duke.exception.StartDateAfterEndDateException;

public abstract class Task {

    private static final PriorityEnum DEFAULT_PRIORITY = PriorityEnum.MEDIUM;
    protected static final RecurrenceEnum DEFAULT_RECURRENCE = RecurrenceEnum.NONE;

    private static final String TASK_ENTRY_DESCRIPTION_REGEX = "%s <%s> {%s}";

    private static final String DESCRIPTION_NOT_EMPTY_ASSERTION = "description should not be empty.";
    private static final String DESCRIPTION_NOT_NULL_ASSERTION = "description should not be null.";
    private static final String PRIORITY_ASSERTION = "priority should not be null.";
    private static final String RECURRENCE_ASSERTION = "recurrence should not be null.";

    private String description;
    private PriorityEnum priority;
    private RecurrenceEnum recurrence;
    private Reminder reminder;

    protected Task(String description) {
        setDescription(description);
        setPriority(DEFAULT_PRIORITY);
        setRecurrence(DEFAULT_RECURRENCE);
    }

    protected Task(String description, PriorityEnum priority) {
        this(description);
        setPriority(priority);
    }

    protected Task(String description, RecurrenceEnum recurrence) {
        this(description);
        setRecurrence(recurrence);
    }

    protected Task(String description, PriorityEnum priority, RecurrenceEnum recurrence) {
        this(description);
        setPriority(priority);
        setRecurrence(recurrence);
    }

    public String getTaskEntryDescription() {
        return String.format(TASK_ENTRY_DESCRIPTION_REGEX, getDescription(), getPriority(), getRecurrence());
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        assert !description.equals("") : DESCRIPTION_NOT_EMPTY_ASSERTION;
        assert description != null : DESCRIPTION_NOT_NULL_ASSERTION;
        this.description = description;
    }

    public PriorityEnum getPriority() {
        return priority;
    }

    public void setPriority(PriorityEnum priority) {
        assert priority != null : PRIORITY_ASSERTION;
        this.priority = priority;
    }

    public Reminder getReminder() {
        return reminder;
    }

    public String getReminder(LocalDateTime now) {
        return reminder.getRecurrenceMessage(now, getTaskEntryDescription(), getRecurrence());
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    public boolean needReminder() {
        return (reminder != null);
    }

    public void updateReminderMessage(String message) {
        reminder.setMessage(message);
    }

    public void updateReminderTime(long reminderTime) {
        reminder.setUserTime(reminderTime);
    }

    public ReminderInformation getReminderInformation() {
        return reminder.getInformation();
    }

    public RecurrenceEnum getRecurrence() {
        return this.recurrence;
    }

    public void setRecurrence(RecurrenceEnum recurrence) {
        assert recurrence != null : RECURRENCE_ASSERTION;
        this.recurrence = recurrence;
    }

    public abstract TypeEnum getTaskType();

    //@@author SeanRobertDH
    /**
     * Edits the variables of {@link seedu.duke.task.Task} based off the flags in <code>arguments</code>.
     *
     * @param arguments <code>Map&lt;String, String&gt;</code> of
     *                  flags to values that should be edited in {@link seedu.duke.task.Task}.
     * @throws InvalidPriorityException if attempting to edit
     *     {@link #priority} and the <code>priority</code> specified is invalid.
     * @throws InvalidRecurrenceException if attempting to edit
     *     {@link #recurrence} and the <code>recurrence</code> specified is invalid.
     * @throws ParseDateFailedException From {@link #taskEdit(java.util.Map)}.
     * @throws StartDateAfterEndDateException From {@link #taskEdit(java.util.Map)}.
     * @throws java.net.URISyntaxException From {@link #taskEdit(java.util.Map)}.
     */
    public void edit(Map<String, String> arguments) throws InvalidPriorityException,
        InvalidRecurrenceException, ParseDateFailedException, StartDateAfterEndDateException, URISyntaxException {

        if (arguments.containsKey(TaskFlag.EDIT_DESCRIPTION)) {
            setDescription(arguments.get(TaskFlag.EDIT_DESCRIPTION));
        }
        if (arguments.containsKey(TaskFlag.PRIORITY)) {
            String priority = arguments.get(TaskFlag.PRIORITY);
            setPriority(PriorityEnum.getPriority(priority));
        }
        if (arguments.containsKey(TaskFlag.RECURRENCE)) {
            String recurrence = arguments.get(TaskFlag.RECURRENCE);
            setRecurrence(RecurrenceEnum.getRecurrence(recurrence));
        }
        taskEdit(arguments);
    }

    //@@author SeanRobertDH
    /**
     * Edits the variables of subclass of {@link seedu.duke.task.Task} based off the flags in <code>arguments</code>.
     *
     * @param arguments <code>Map&lt;String, String&gt;</code> of flags to values that should be edited in subclasses.
     * @throws seedu.duke.exception.ParseDateFailedException if unable to parse a
     *     value when editing a {@link java.time.LocalDateTime}.
     * @throws StartDateAfterEndDateException From {@link seedu.duke.task.type.Event}.
     * @throws java.net.URISyntaxException From {@link seedu.duke.task.type.Lesson}.
     */
    protected abstract void taskEdit(Map<String, String> arguments)
        throws ParseDateFailedException, StartDateAfterEndDateException, URISyntaxException;

    //@@author SeanRobertDH
    /**
     * Checks that all the keys in <code>Map&lt;String, String&gt; arguments</code> are valid edit flags.
     *
     * @param arguments <code>Map&lt;String, String&gt;</code> of flags to values to be checked for valid edit flags.
     * @throws InvalidFlagsException If <code>arguments</code> has invalid flags.
     */
    public void checkAllEditFlagsValid(Map<String, String> arguments) throws InvalidFlagsException {
        HashSet<String> validFlags = new HashSet<>(getTaskFlag().getAllEditFlags());
        ArrayList<String> invalidFlags = new ArrayList<>();
        for (String flag : arguments.keySet()) {
            if (flag == Command.MAIN_ARGUMENT) {
                continue;
            }
            if (!validFlags.contains(flag)) {
                invalidFlags.add(flag);
            }
        }
        if (!invalidFlags.isEmpty()) {
            throw new InvalidFlagsException(invalidFlags);
        }
    }

    //@@author SeanRobertDH
    protected abstract TaskFlag getTaskFlag();

    //@@author SeanRobertDH
    /**
     * Returns the {@link java.time.LocalDateTime} that is the most relevant for the Task subclass.
     */
    public abstract LocalDateTime getListDate();

    //@@author SeanRobertDH

    /**
     * Updates the {@link java.time.LocalDateTime} in subclasses if they have a recurrence
     * to the latest date.
     */
    public abstract void refreshDate();
}
