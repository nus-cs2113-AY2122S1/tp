package seedu.duke.task.factory;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.exception.InvalidPriorityException;
import seedu.duke.exception.InvalidRecurrenceException;
import seedu.duke.exception.ParseTaskFailedException;
import seedu.duke.exception.RecurrenceWithoutDateException;
import seedu.duke.log.Log;
import seedu.duke.command.flags.TodoFlag;
import seedu.duke.exception.RequiredArgmentNotProvidedException;
import seedu.duke.parser.TaskParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.type.Todo;

public class TodoFactory {
    private static final TypeEnum taskType = TypeEnum.TODO;

    static Todo getTodo(HashMap<String, String> flags) throws GetTaskFailedException {
        try {
            hasRequiredArguments(flags);

            String description = flags.get(TodoFlag.DESCRIPTION);
            String priority = flags.get(TodoFlag.PRIORITY);
            String doOn = flags.get(TodoFlag.DO_ON_DATE);
            String recurrence = flags.get(TodoFlag.RECURRENCE);

            PriorityEnum priorityEnum = TaskParser.getPriorityEnum(priority);
            Date doOnDate = TaskParser.getDate(doOn);
            RecurrenceEnum recurrenceEnum = TaskParser.getRecurrenceEnum(recurrence);

            boolean hasRecurrence = recurrenceEnum != null && recurrenceEnum != RecurrenceEnum.NONE;
            if (doOnDate == null && hasRecurrence) {
                throw new RecurrenceWithoutDateException();
            }

            return getConstructor(description, priorityEnum, doOnDate, recurrenceEnum);

        } catch (RequiredArgmentNotProvidedException ranpe) {
            Log.severe(ranpe.getMessage());
        } catch (ParseException pe) {
            Log.severe(pe.getMessage());
        } catch (InvalidPriorityException ipe) {
            Log.severe(ipe.getMessage());
        } catch (InvalidRecurrenceException ire) {
            Log.severe(ire.getMessage());
        } catch (RecurrenceWithoutDateException rwde) {
            Log.severe(rwde.getMessage());
        }
        throw new GetTaskFailedException(taskType.toString());
    }

    private static void hasRequiredArguments(HashMap<String, String> flags)
            throws RequiredArgmentNotProvidedException {
        for (String requiredArgument : TodoFlag.REQUIRED_FLAGS) {
            String flag = flags.get(requiredArgument);
            if (flag == null) {
                throw new RequiredArgmentNotProvidedException(requiredArgument, taskType.toString());
            }
        }
    }

    private static Todo getConstructor(String description,
            PriorityEnum priority, Date doOn, RecurrenceEnum recurrence) {
        if (priority == null) {
            return getTodoWithDefaultPriority(description, doOn, recurrence);
        } else {
            return getTodoWithPriority(description, priority, doOn, recurrence);
        }
    }

    private static Todo getTodoWithDefaultPriority(String description, Date doOn, RecurrenceEnum recurrence) {
        if (doOn == null) {
            return new Todo(description);
        } else {
            if (recurrence == null) {
                return new Todo(description, doOn);
            } else {
                return new Todo(description, doOn, recurrence);
            }
        }
    }

    private static Todo getTodoWithPriority(String description,
            PriorityEnum priority, Date doOn, RecurrenceEnum recurrence) {
        if (doOn == null) {
            return new Todo(description, priority);
        } else {
            if (recurrence == null) {
                return new Todo(description, priority, doOn);
            } else {
                return new Todo(description, priority, doOn, recurrence);
            }
        }
    }
}
