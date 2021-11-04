package seedu.duke.task.factory;

import java.time.LocalDateTime;
import java.util.Map;
import seedu.duke.command.flags.TaskFlag;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.exception.InvalidPriorityException;
import seedu.duke.exception.InvalidRecurrenceException;
import seedu.duke.exception.ParseDateFailedException;
import seedu.duke.exception.RequiredArgmentNotProvidedException;
import seedu.duke.parser.DateParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.TypeEnum;
import seedu.duke.utility.Utility;

//@@author SeanRobertDH
/**
 * Task factory template to be extended in superclasses of Task factories.
 */
public abstract class TaskFactory {
    private final TypeEnum taskType;
    private final String[] requiredFlags;
    final Map<String, String> flags;

    String description;
    PriorityEnum priorityEnum;
    RecurrenceEnum recurrenceEnum;

    /**
     * {@link seedu.duke.task.factory.TaskFactory} Constructor.
     *
     * @param taskType {@link seedu.duke.task.TypeEnum} of the
     *     respective {@link seedu.duke.task.Task} the factory is for.
     * @param requiredFlags Required flags that are to be present in <code>flags</code> for
     *     {@link seedu.duke.task.Task} creation to be valid.
     * @param flags the <code>Map&lt;String, String&gt;</code> of flags to their values.
     */
    TaskFactory(TypeEnum taskType, String[] requiredFlags, Map<String, String> flags) {
        this.taskType = taskType;
        this.requiredFlags = requiredFlags;
        this.flags = flags;
    }

    //Template Method Pattern
    /**
     * The Template Method Pattern for superclasses of Task factories.
     * This method should be overwritten to Downcast the return task type.
     * @throws GetTaskFailedException General Exception
     *     thrown when creating {@link seedu.duke.task.Task} fails.
     */
    public Task getTask() throws GetTaskFailedException {
        try {
            checkForRequiredArguments(flags);

            description = flags.get(TaskFlag.DESCRIPTION);

            String priority = flags.get(TaskFlag.PRIORITY);
            priorityEnum = getPriorityEnum(priority);

            String recurrence = flags.get(TaskFlag.RECURRENCE);
            recurrenceEnum = getRecurrenceEnum(recurrence);

            setAdditionalVariables();
            return createTask();
        } catch (RequiredArgmentNotProvidedException ranpe) {
            throw new GetTaskFailedException(ranpe.getMessage());
        } catch (InvalidPriorityException ipe) {
            throw new GetTaskFailedException(ipe.getMessage());
        } catch (InvalidRecurrenceException ire) {
            throw new GetTaskFailedException(ire.getMessage());
        } catch (GetTaskFailedException gtfe) {
            throw new GetTaskFailedException(gtfe.getMessage());
        }
    }

    /**
     * Checks that all required flags are present in <code>flags</code>.
     */
    private void checkForRequiredArguments(Map<String, String> flags) throws RequiredArgmentNotProvidedException {
        for (String requiredArgument : requiredFlags) {
            String requiredFlag = flags.get(requiredArgument);
            if (requiredFlag == null) {
                throw new RequiredArgmentNotProvidedException(requiredArgument, taskType.toString());
            }
        }
    }

    /**
     * Sets the respective variables in that Task and all unique Exceptions should be
     * caught with the message passed to {@link seedu.duke.exception.GetTaskFailedException}.
     * To be overwritten by subclasses.
     * @throws GetTaskFailedException General Exception
     *     thrown when creating {@link seedu.duke.task.Task} fails.
     */
    abstract void setAdditionalVariables() throws GetTaskFailedException;

    /**
     * Returns the {@link seedu.duke.task.Task} created.
     * Function should be used to decide which constructor to use based on what optional
     * arguments are present.
     * @return created {@link seedu.duke.task.Task}.
     */
    abstract Task createTask();

    /**
     * Returns the {@link seedu.duke.task.PriorityEnum} based on <code>priority</code>
     * unless <code>priority</code> is <code>null</code> then it returns <code>null</code>.
     * @return {@link seedu.duke.task.PriorityEnum} based on <code>priority</code>.
     * @throws seedu.duke.exception.InvalidPriorityException If <code>priority</code> is
     *     not <code>null</code> and it does not correspond to a valid {@link seedu.duke.task.PriorityEnum}.
     */
    private PriorityEnum getPriorityEnum(String priority) throws InvalidPriorityException {
        if (priority == null) {
            return null;
        }
        if (Utility.isInteger(priority)) {
            return PriorityEnum.getPriority(Integer.parseInt(priority));
        } else {
            return PriorityEnum.getPriority(priority);
        }
    }

    /**
     * Returns the {@link seedu.duke.task.RecurrenceEnum} based on <code>recurrence</code>
     * unless <code>recurrence</code> is <code>null</code> then it returns <code>null</code>.
     * @return {@link seedu.duke.task.RecurrenceEnum} based on <code>recurrence</code>.
     * @throws seedu.duke.exception.InvalidRecurrenceException If <code>recurrence</code> is
     *     not <code>null</code> and it does not correspond to a valid {@link seedu.duke.task.RecurrenceEnum}.
     */
    private RecurrenceEnum getRecurrenceEnum(String recurrence) throws InvalidRecurrenceException {
        if (recurrence == null) {
            return null;
        }
        return RecurrenceEnum.getRecurrence(recurrence);
    }

    /**
     * Returns the {@link java.time.LocalDateTime} based on <code>date</code>
     * unless <code>date</code> is <code>null</code> then it returns <code>null</code>.
     * @return {@link java.time.LocalDateTime} based on <code>date</code>.
     * @throws seedu.duke.exception.ParseDateFailedException If <code>date</code> is
     *     not <code>null</code> and it is unable to be parsed.
     */
    static LocalDateTime getDate(String date) throws ParseDateFailedException {
        if (date == null) {
            return null;
        }
        return DateParser.stringToDate(date);
    }
}
