package seedu.duke.task.factory;

import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.exception.InvalidPriorityException;
import seedu.duke.exception.InvalidRecurrenceException;
import seedu.duke.log.Log;
import seedu.duke.command.flags.DeadlineFlag;
import seedu.duke.exception.RequiredArgmentNotProvidedException;
import seedu.duke.parser.TaskParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.type.Deadline;

public class DeadlineFactory {
    private static final TypeEnum taskType = TypeEnum.DEADLINE;

    static Deadline getDeadline(HashMap<String, String> flags) throws GetTaskFailedException {
        try {
            hasRequiredArguments(flags);

            String description = flags.get(DeadlineFlag.DESCRIPTION);
            String due = flags.get(DeadlineFlag.DUE_DATE);
            String priority = flags.get(DeadlineFlag.PRIORITY);
            String recurrence = flags.get(DeadlineFlag.RECURRENCE);

            Date dueDate = TaskParser.getDate(due);
            PriorityEnum priorityEnum = TaskParser.getPriorityEnum(priority);
            RecurrenceEnum recurrenceEnum = TaskParser.getRecurrenceEnum(recurrence);

            return getConstructor(description, dueDate, priorityEnum, recurrenceEnum);

        } catch (RequiredArgmentNotProvidedException ranpe) {
            Log.severe(ranpe.getMessage());
        } catch (ParseException pe) {
            Log.severe(pe.getMessage());
        } catch (InvalidPriorityException ipe) {
            Log.severe(ipe.getMessage());
        } catch (InvalidRecurrenceException ire) {
            Log.severe(ire.getMessage());
        }
        throw new GetTaskFailedException(taskType.toString());
    }

    private static void hasRequiredArguments(HashMap<String, String> flags) throws RequiredArgmentNotProvidedException {
        for (String requiredArgument : DeadlineFlag.REQUIRED_FLAGS) {
            String flag = flags.get(requiredArgument);
            if (flag == null) {
                throw new RequiredArgmentNotProvidedException(requiredArgument, taskType.toString());
            }
        }
    }

    private static Deadline getConstructor(String description,
            Date due, PriorityEnum priority, RecurrenceEnum recurrence) {
        if (priority == null) {
            return getDeadlineWithDefaultPriority(description, due, recurrence);
        } else {
            return getDeadlineWithPriority(description, due, priority, recurrence);
        }
    }

    private static Deadline getDeadlineWithDefaultPriority(String description, Date due, RecurrenceEnum recurrence) {
        if (recurrence == null) {
            return new Deadline(description, due);
        } else {
            return new Deadline(description, due, recurrence);
        }
    }

    private static Deadline getDeadlineWithPriority(String description,
            Date due, PriorityEnum priority, RecurrenceEnum recurrence) {
        if (recurrence == null) {
            return new Deadline(description, due, priority);
        } else {
            return new Deadline(description, due, priority, recurrence);
        }
    }
}
