package seedu.tp.task.factory;

import java.time.LocalDateTime;
import java.util.Map;

import seedu.tp.exception.GetTaskFailedException;
import seedu.tp.exception.ParseDateFailedException;
import seedu.tp.command.flags.TodoFlag;
import seedu.tp.task.Task;
import seedu.tp.task.TypeEnum;
import seedu.tp.task.type.Todo;

//@@author SeanRobertDH
/**
 * Factory class used to create {@link seedu.tp.task.type.Todo}.
 */
public class TodoFactory extends TaskFactory {
    private static final TypeEnum TASK_TYPE = TypeEnum.TODO;

    LocalDateTime doOnDate;


    /**
     * Constructor for {@link seedu.tp.task.factory.TodoFactory}.
     *
     * @param flags the <code>Map&lt;String, String&gt;</code> of flags to their values.
     */
    public TodoFactory(Map<String, String> flags) {
        super(TASK_TYPE, TodoFlag.REQUIRED_FLAGS, flags);
    }


    @Override
    void setAdditionalVariables() throws GetTaskFailedException {
        try {
            String doOn = flags.get(TodoFlag.DO_ON_DATE);
            doOnDate = getDate(doOn);
        } catch (ParseDateFailedException pdfe) {
            throw new GetTaskFailedException(pdfe.getMessage());
        }
    }

    @Override
    Task createTask() {
        if (doOnDate == null) {
            return new Todo(description);
        } else {
            return new Todo(description, doOnDate);
        }
    }

    /**
     * Returns the {@link seedu.tp.task.type.Todo} created.
     *
     * @return created {@link seedu.tp.task.type.Todo}.
     * @throws GetTaskFailedException General Exception
     *     thrown when creating {@link seedu.tp.task.Task} fails.
     */
    @Override
    public Todo getTask() throws GetTaskFailedException {
        return (Todo) super.getTask();
    }
}
