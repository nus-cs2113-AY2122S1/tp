package seedu.duke.task.factory;

import java.util.Date;
import java.util.HashMap;
import seedu.duke.exception.GetTaskFailedException;
import seedu.duke.exception.InvalidPriorityException;
import seedu.duke.exception.InvalidRecurrenceException;
import seedu.duke.exception.ParseDateFailedException;
import seedu.duke.exception.StartDateAfterEndDateException;
import seedu.duke.command.flags.EventFlag;
import seedu.duke.exception.RequiredArgmentNotProvidedException;
import seedu.duke.parser.TaskParser;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.type.Event;

public class EventFactory {
    private static final TypeEnum taskType = TypeEnum.EVENT;

    public static Event getEvent(HashMap<String, String> flags) throws GetTaskFailedException {
        try {
            checkForRequiredArguments(flags);

            String description = flags.get(EventFlag.DESCRIPTION);
            String start = flags.get(EventFlag.START_DATE);
            String end = flags.get(EventFlag.END_DATE);
            String priority = flags.get(EventFlag.PRIORITY);
            String recurrence = flags.get(EventFlag.RECURRENCE);

            Date startDate = TaskParser.getDate(start);
            Date endDate = TaskParser.getDate(end);
            PriorityEnum priorityEnum = TaskParser.getPriorityEnum(priority);
            RecurrenceEnum recurrenceEnum = TaskParser.getRecurrenceEnum(recurrence);

            if (startDate.after(endDate)) {
                throw new StartDateAfterEndDateException();
            }

            return getConstructor(description, startDate, endDate, priorityEnum, recurrenceEnum);
        } catch (RequiredArgmentNotProvidedException ranpe) {
            throw new GetTaskFailedException(ranpe.getMessage());
        } catch (ParseDateFailedException pdfe) {
            throw new GetTaskFailedException(pdfe.getMessage());
        } catch (InvalidPriorityException ipe) {
            throw new GetTaskFailedException(ipe.getMessage());
        } catch (InvalidRecurrenceException ire) {
            throw new GetTaskFailedException(ire.getMessage());
        } catch (StartDateAfterEndDateException sdaede) {
            throw new GetTaskFailedException(sdaede.getMessage());
        }
    }

    private static void checkForRequiredArguments(HashMap<String, String> flags)
        throws RequiredArgmentNotProvidedException {
        for (String requiredArgument : EventFlag.REQUIRED_FLAGS) {
            String flag = flags.get(requiredArgument);
            if (flag == null) {
                throw new RequiredArgmentNotProvidedException(requiredArgument, taskType.toString());
            }
        }
    }

    private static Event getConstructor(String description,
            Date start, Date end, PriorityEnum priority, RecurrenceEnum recurrence) {
        if (priority == null) {
            return getEventWithDefaultPriority(description, start, end, recurrence);
        } else {
            return getEventWithPriority(description, start, end, priority, recurrence);
        }
    }

    private static Event getEventWithDefaultPriority(String description,
            Date start, Date end, RecurrenceEnum recurrence) {
        if (recurrence == null) {
            return new Event(description, start, end);
        } else {
            return new Event(description, start, end, recurrence);
        }
    }

    private static Event getEventWithPriority(String description,
            Date start, Date end, PriorityEnum priority, RecurrenceEnum recurrence) {
        if (recurrence == null) {
            return new Event(description,
                start, end, priority);
        } else {
            return new Event(description, start, end, priority, recurrence);
        }
    }
}
