package seedu.tp.task.factory;

import java.time.LocalDateTime;
import java.util.Map;
import seedu.tp.command.flags.TaskFlag;
import seedu.tp.exception.GetTaskFailedException;
import seedu.tp.exception.InvalidPriorityException;
import seedu.tp.exception.InvalidRecurrenceException;
import seedu.tp.exception.ParseDateFailedException;
import seedu.tp.exception.RequiredArgmentNotProvidedException;
import seedu.tp.parser.DateParser;
import seedu.tp.task.PriorityEnum;
import seedu.tp.task.RecurrenceEnum;
import seedu.tp.task.Task;
import seedu.tp.task.TypeEnum;
import seedu.tp.utility.Utility;

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
     * {@link seedu.tp.task.factory.TaskFactory} Constructor.
     *
     * @param taskType {@link seedu.tp.task.TypeEnum} of the
     *     respective {@link seedu.tp.task.Task} the factory is for.
     * @param requiredFlags Required flags that are to be present in <code>flags</code> for
     *     {@link seedu.tp.task.Task} creation to be valid.
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
     *     thrown when creating {@link seedu.tp.task.Task} fails.
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

            Task task = createTask();
            setOptionalTaskVariables(task);

            return task;
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

    private void setOptionalTaskVariables(Task task) {
        if (priorityEnum != null) {
            task.setPriority(priorityEnum);
        }
        if (recurrenceEnum != null) {
            task.setRecurrence(recurrenceEnum);
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
     * caught with the message passed to {@link seedu.tp.exception.GetTaskFailedException}.
     * To be overwritten by subclasses.
     * @throws GetTaskFailedException General Exception
     *     thrown when creating {@link seedu.tp.task.Task} fails.
     */
    abstract void setAdditionalVariables() throws GetTaskFailedException;

    /**
     * Returns the {@link seedu.tp.task.Task} created.
     * Function should be used to decide which constructor to use based on what optional
     * arguments are present.
     * @return created {@link seedu.tp.task.Task}.
     */
    abstract Task createTask();

    /**
     * Returns the {@link seedu.tp.task.PriorityEnum} based on <code>priority</code>
     * unless <code>priority</code> is <code>null</code> then it returns <code>null</code>.
     * @return {@link seedu.tp.task.PriorityEnum} based on <code>priority</code>.
     * @throws seedu.tp.exception.InvalidPriorityException If <code>priority</code> is
     *     not <code>null</code> and it does not correspond to a valid {@link seedu.tp.task.PriorityEnum}.
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
     * Returns the {@link seedu.tp.task.RecurrenceEnum} based on <code>recurrence</code>
     * unless <code>recurrence</code> is <code>null</code> then it returns <code>null</code>.
     * @return {@link seedu.tp.task.RecurrenceEnum} based on <code>recurrence</code>.
     * @throws seedu.tp.exception.InvalidRecurrenceException If <code>recurrence</code> is
     *     not <code>null</code> and it does not correspond to a valid {@link seedu.tp.task.RecurrenceEnum}.
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
     * @throws seedu.tp.exception.ParseDateFailedException If <code>date</code> is
     *     not <code>null</code> and it is unable to be parsed.
     */
    static LocalDateTime getDate(String date) throws ParseDateFailedException {
        if (date == null) {
            return null;
        }
        return DateParser.stringToDate(date);
    }
}
