package seedu.duke.task.factory;

import java.util.HashMap;
import seedu.duke.log.Log;
import seedu.duke.command.flags.TodoFlag;
import seedu.duke.exception.RecurrenceWithoutDateException;
import seedu.duke.exception.RequiredArgmentNotProvidedException;
import seedu.duke.parser.TaskParser;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.factory.arguments.TodoArguments;
import seedu.duke.task.type.Todo;
import seedu.duke.utility.Utility;

public class TodoFactory {
    private static final TypeEnum taskType = TypeEnum.TODO;

    private static final String[] requiredArguments = {TodoFlag.DESCRIPTION};

    static Todo getTodo(HashMap<String, String> flags) {
        try {
            hasRequiredArguments(flags);

            String description = flags.get(TodoFlag.DESCRIPTION);
            String priority = flags.get(TodoFlag.PRIORITY);
            String doOnDate = flags.get(TodoFlag.DO_ON_DATE);
            String recurrence = flags.get(TodoFlag.RECURRENCE);

            TodoArguments arguments = TaskParser.parseTodoArguments(description, priority, doOnDate, recurrence);
            return getConstructor(arguments);

        } catch (RequiredArgmentNotProvidedException ranpe) {
            Log.getLogger(TodoFactory.class).severe(ranpe.getMessage());
        } catch (RecurrenceWithoutDateException rwde) {
            Log.getLogger(TodoFactory.class).severe(rwde.getMessage());
        }
        Utility.assertEndOfFunctionUnreachable();
        return null;
    }

    private static void hasRequiredArguments(HashMap<String, String> flags)
            throws RequiredArgmentNotProvidedException, RecurrenceWithoutDateException {
        for (String requiredArgument : requiredArguments) {
            String flag = flags.get(requiredArgument);
            if (flag == null) {
                throw new RequiredArgmentNotProvidedException(requiredArgument, taskType.toString());
            }
        }
        if (flags.get(TodoFlag.DO_ON_DATE) == null && flags.get(TodoFlag.RECURRENCE) != null) {
            throw new RecurrenceWithoutDateException();
        }
    }

    private static Todo getConstructor(TodoArguments arguments) {
        if (arguments.getPriority() == null) {
            return getTodoWithNoPriority(arguments);
        } else {
            return getTodoWithPriority(arguments);
        }
    }

    private static Todo getTodoWithNoPriority(TodoArguments arguments) {
        if (arguments.getDoOnDate() == null) {
            return new Todo(arguments.getDescription());
        } else {
            if (arguments.getRecurrence() == null) {
                return new Todo(arguments.getDescription(), arguments.getDoOnDate());
            } else {
                return new Todo(arguments.getDescription(), arguments.getDoOnDate(), arguments.getRecurrence());
            }
        }
    }

    private static Todo getTodoWithPriority(TodoArguments arguments) {
        if (arguments.getDoOnDate() == null) {
            return new Todo(arguments.getDescription(), arguments.getPriority());
        } else {
            if (arguments.getRecurrence() == null) {
                return new Todo(arguments.getDescription(), arguments.getPriority(),
                        arguments.getDoOnDate());
            } else {
                return new Todo(arguments.getDescription(), arguments.getPriority(),
                        arguments.getDoOnDate(), arguments.getRecurrence());
            }
        }
    }
}
