package seedu.duke.task.factory;

import java.util.HashMap;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.exception.ParseTaskFailedException;
import seedu.duke.log.Log;
import seedu.duke.command.flags.EventFlag;
import seedu.duke.exception.RequiredArgmentNotProvidedException;
import seedu.duke.parser.TaskParser;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.factory.arguments.EventArguments;
import seedu.duke.task.type.Event;

public class EventFactory {
    private static final TypeEnum taskType = TypeEnum.EVENT;

    static Event getEvent(HashMap<String, String> flags) throws GetTaskFailedException {
        try {
            hasRequiredArguments(flags);

            String description = flags.get(EventFlag.DESCRIPTION);
            String startDate = flags.get(EventFlag.START_DATE);
            String endDate = flags.get(EventFlag.END_DATE);
            String priority = flags.get(EventFlag.PRIORITY);
            String recurrence = flags.get(EventFlag.RECURRENCE);

            EventArguments arguments = TaskParser.parseEventArguments(description,
                startDate, endDate, priority, recurrence);
            return getConstructor(arguments);

        } catch (RequiredArgmentNotProvidedException ranpe) {
            Log.getLogger(EventFactory.class).severe(ranpe.getMessage());
        } catch (ParseTaskFailedException ptfe) {
            Log.getLogger(EventFactory.class).warning(ptfe.getMessage());
        }
        throw new GetTaskFailedException(taskType.toString());
    }

    private static void hasRequiredArguments(HashMap<String, String> flags)
            throws RequiredArgmentNotProvidedException {
        for (String requiredArgument : EventFlag.REQUIRED_FLAGS) {
            String flag = flags.get(requiredArgument);
            if (flag == null) {
                throw new RequiredArgmentNotProvidedException(requiredArgument, taskType.toString());
            }
        }
    }

    private static Event getConstructor(EventArguments arguments) {
        if (arguments.getPriority() == null) {
            return getEventWithDefaultPriority(arguments);
        } else {
            return getEventWithPriority(arguments);
        }
    }

    private static Event getEventWithDefaultPriority(EventArguments arguments) {
        if (arguments.getRecurrence() == null) {
            return new Event(arguments.getDescription(), arguments.getStartDate(), arguments.getEndDate());
        } else {
            return new Event(arguments.getDescription(),
                    arguments.getStartDate(), arguments.getEndDate(), arguments.getRecurrence());
        }
    }

    private static Event getEventWithPriority(EventArguments arguments) {
        if (arguments.getRecurrence() == null) {
            return new Event(arguments.getDescription(),
                    arguments.getStartDate(), arguments.getEndDate(), arguments.getPriority());
        } else {
            return new Event(arguments.getDescription(),
                    arguments.getStartDate(), arguments.getEndDate(),
                    arguments.getPriority(), arguments.getRecurrence());
        }
    }
}
