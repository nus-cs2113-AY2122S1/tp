package seedu.tp.task.factory;

import java.time.LocalDateTime;
import java.util.Map;
import seedu.tp.command.flags.DeadlineFlag;
import seedu.tp.exception.GetTaskFailedException;
import seedu.tp.exception.ParseDateFailedException;
import seedu.tp.task.Task;
import seedu.tp.task.TypeEnum;
import seedu.tp.task.type.Deadline;


//@@author SeanRobertDH
/**
 * Factory class used to create {@link seedu.tp.task.type.Deadline}.
 */
public class DeadlineFactory extends TaskFactory {

    private static final TypeEnum TASK_TYPE = TypeEnum.DEADLINE;

    LocalDateTime dueDate;

    /**
     * Constructor for {@link seedu.tp.task.factory.DeadlineFactory}.
     *
     * @param flags the <code>Map&lt;String, String&gt;</code> of flags to their values.
     */
    public DeadlineFactory(Map<String, String> flags) {
        super(TASK_TYPE, DeadlineFlag.REQUIRED_FLAGS, flags);
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
        return new Deadline(description, dueDate);
    }

    /**
     * Returns the {@link seedu.tp.task.type.Deadline} created.
     *
     * @return created {@link seedu.tp.task.type.Deadline}.
     * @throws GetTaskFailedException General Exception
     *     thrown when creating {@link seedu.tp.task.Task} fails.
     */
    @Override
    public Deadline getTask() throws GetTaskFailedException {
        return (Deadline) super.getTask();
    }
}
