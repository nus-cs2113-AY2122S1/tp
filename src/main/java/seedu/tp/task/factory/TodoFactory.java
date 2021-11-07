package seedu.tp.task.factory;

import java.time.LocalDateTime;
import java.util.Map;

import seedu.tp.exception.GetTaskFailedException;
import seedu.tp.exception.ParseDateFailedException;
import seedu.tp.command.flags.TodoFlag;
import seedu.tp.task.PriorityEnum;
import seedu.tp.task.RecurrenceEnum;
import seedu.tp.task.Task;
import seedu.tp.task.TypeEnum;
import seedu.tp.task.type.Todo;

//@@author SeanRobertDH
/**
 * Factory class used to create {@link seedu.tp.task.type.Todo}.
 */
public class TodoFactory extends TaskFactory {
    private static final TypeEnum taskType = TypeEnum.TODO;

    LocalDateTime doOnDate;


    /**
     * Constructor for {@link seedu.tp.task.factory.TodoFactory}.
     *
     * @param flags the <code>Map&lt;String, String&gt;</code> of flags to their values.
     */
    public TodoFactory(Map<String, String> flags) {
        super(taskType, TodoFlag.REQUIRED_FLAGS, flags);
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
        if (priorityEnum == null) {
            return getTodoWithDefaultPriority(description, doOnDate, recurrenceEnum);
        } else {
            return getTodoWithPriority(description, priorityEnum, doOnDate, recurrenceEnum);
        }
    }

    private static Todo getTodoWithDefaultPriority(String description, LocalDateTime doOn, RecurrenceEnum recurrence) {
        if (doOn == null) {
            if (recurrence == null) {
                return new Todo(description);
            } else {
                return new Todo(description, recurrence);
            }
        } else {
            if (recurrence == null) {
                return new Todo(description, doOn);
            } else {
                return new Todo(description, doOn, recurrence);
            }
        }
    }

    private static Todo getTodoWithPriority(String description,
                                            PriorityEnum priority, LocalDateTime doOn, RecurrenceEnum recurrence) {
        if (doOn == null) {
            if (recurrence == null) {
                return new Todo(description, priority);
            } else {
                return new Todo(description, priority, recurrence);
            }
        } else {
            if (recurrence == null) {
                return new Todo(description, priority, doOn);
            } else {
                return new Todo(description, priority, doOn, recurrence);
            }
        }
    }

    /**
     * Returns the {@link seedu.tp.task.type.Todo} created.
     *
     * @return created {@link seedu.tp.task.type.Todo}.
     * @throws seedu.tp.exception.GetTaskFailedException General Exception
     *     thrown when creating {@link seedu.tp.task.Task} fails.
     */
    @Override
    public Todo getTask() throws GetTaskFailedException {
        return (Todo) super.getTask();
    }
}
