package seedu.duke.task.factory;

import java.time.LocalDateTime;
import java.util.Map;
import seedu.duke.command.flags.DeadlineFlag;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.exception.ParseDateFailedException;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.Task;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.type.Deadline;


//@@author SeanRobertDH
/**
 * Factory class used to create {@link seedu.duke.task.type.Deadline}.
 */
public class DeadlineFactory extends TaskFactory {

    private static final TypeEnum taskType = TypeEnum.DEADLINE;

    LocalDateTime dueDate;

    /**
     * Constructor for {@link seedu.duke.task.factory.DeadlineFactory}.
     *
     * @param flags the <code>Map&lt;String, String&gt;</code> of flags to their values.
     */
    public DeadlineFactory(Map<String, String> flags) {
        super(taskType, DeadlineFlag.REQUIRED_FLAGS, flags);
    }


    @Override
    void setAdditionalVariables() throws GetTaskFailedException {
        try {
            String due = flags.get(DeadlineFlag.DUE_DATE);
            dueDate = getDate(due);
        } catch (ParseDateFailedException pdfe) {
            throw new GetTaskFailedException(pdfe.getMessage());
        }
    }

    @Override
    Task createTask() {
        if (priorityEnum == null) {
            return getDeadlineWithDefaultPriority(description, dueDate, recurrenceEnum);
        } else {
            return getDeadlineWithPriority(description, dueDate, priorityEnum, recurrenceEnum);
        }
    }

    private Deadline getDeadlineWithDefaultPriority(String description,
            LocalDateTime due, RecurrenceEnum recurrence) {
        if (recurrence == null) {
            return new Deadline(description, due);
        } else {
            return new Deadline(description, due, recurrence);
        }
    }

    private Deadline getDeadlineWithPriority(String description,
            LocalDateTime due, PriorityEnum priority, RecurrenceEnum recurrence) {
        if (recurrence == null) {
            return new Deadline(description, due, priority);
        } else {
            return new Deadline(description, due, priority, recurrence);
        }
    }

    /**
     * Returns the {@link seedu.duke.task.type.Deadline} created.
     *
     * @return created {@link seedu.duke.task.type.Deadline}.
     * @throws seedu.duke.exception.GetTaskFailedException General Exception
     *     thrown when creating {@link seedu.duke.task.Task} fails.
     */
    @Override
    public Deadline getTask() throws GetTaskFailedException {
        return (Deadline) super.getTask();
    }
}
