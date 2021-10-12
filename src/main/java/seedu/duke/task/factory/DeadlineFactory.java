package seedu.duke.task.factory;

import java.util.HashMap;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.exception.ParseTaskFailedException;
import seedu.duke.log.Log;
import seedu.duke.command.flags.DeadlineFlag;
import seedu.duke.exception.RequiredArgmentNotProvidedException;
import seedu.duke.parser.TaskParser;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.factory.arguments.DeadlineArguments;
import seedu.duke.task.type.Deadline;

public class DeadlineFactory {
    private static final TypeEnum taskType = TypeEnum.DEADLINE;

    static Deadline getDeadline(HashMap<String, String> flags) throws GetTaskFailedException {
        try {
            hasRequiredArguments(flags);

            String description = flags.get(DeadlineFlag.DESCRIPTION);
            String dueDate = flags.get(DeadlineFlag.DUE_DATE);
            String priority = flags.get(DeadlineFlag.PRIORITY);
            String recurrence = flags.get(DeadlineFlag.RECURRENCE);

            DeadlineArguments arguments = TaskParser.parseDeadlineArguments(description, dueDate, priority, recurrence);
            return getConstructor(arguments);

        } catch (RequiredArgmentNotProvidedException ranpe) {
            Log.getLogger(DeadlineFactory.class).severe(ranpe.getMessage());
        } catch (ParseTaskFailedException ptfe) {
            Log.getLogger(DeadlineFactory.class).warning(ptfe.getMessage());
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

    private static Deadline getConstructor(DeadlineArguments arguments) {
        if (arguments.getPriority() == null) {
            return getDeadlineWithDefaultPriority(arguments);
        } else {
            return getDeadlineWithPriority(arguments);
        }
    }

    private static Deadline getDeadlineWithDefaultPriority(DeadlineArguments arguments) {
        if (arguments.getRecurrence() == null) {
            return new Deadline(arguments.getDescription(), arguments.getDueDate());
        } else {
            return new Deadline(arguments.getDescription(), arguments.getDueDate(), arguments.getRecurrence());
        }
    }

    private static Deadline getDeadlineWithPriority(DeadlineArguments arguments) {
        if (arguments.getRecurrence() == null) {
            return new Deadline(arguments.getDescription(), arguments.getDueDate(), arguments.getPriority());
        } else {
            return new Deadline(arguments.getDescription(), arguments.getDueDate(),
                    arguments.getPriority(), arguments.getRecurrence());
        }
    }
}
