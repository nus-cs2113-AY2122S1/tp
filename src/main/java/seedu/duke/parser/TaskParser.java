package seedu.duke.parser;

import java.text.ParseException;
import java.util.Date;
import seedu.duke.exception.ParseTaskFailedException;
import seedu.duke.exception.RecurrenceWithoutDateException;
import seedu.duke.exception.StartDateAfterEndDateException;
import seedu.duke.log.Log;
import seedu.duke.exception.InvalidPriorityException;
import seedu.duke.exception.InvalidRecurrenceException;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.TypeEnum;
import seedu.duke.task.factory.TodoFactory;
import seedu.duke.task.factory.arguments.DeadlineArguments;
import seedu.duke.task.factory.arguments.EventArguments;
import seedu.duke.task.factory.arguments.TodoArguments;
import seedu.duke.utility.Utility;

public class TaskParser {
    public static TodoArguments parseTodoArguments(String description,
            String priority, String doOn, String recurrence) throws ParseTaskFailedException {
        try {
            PriorityEnum priorityEnum = getPriorityEnum(priority);
            Date doOnDate = getDate(doOn);
            RecurrenceEnum recurrenceEnum = getRecurrenceEnum(recurrence);

            boolean hasRecurrence = recurrenceEnum != null && recurrenceEnum != RecurrenceEnum.NONE;
            if (doOnDate == null && hasRecurrence) {
                throw new RecurrenceWithoutDateException();
            }
            return new TodoArguments(description, priorityEnum, doOnDate, recurrenceEnum);
        } catch (InvalidPriorityException ipe) {
            Log.getLogger(TodoFactory.class).severe(ipe.getMessage());
        } catch (ParseException pe) {
            Log.getLogger(TodoFactory.class).severe(pe.getMessage());
        } catch (InvalidRecurrenceException ire) {
            Log.getLogger(TodoFactory.class).severe(ire.getMessage());
        } catch (RecurrenceWithoutDateException rwde) {
            Log.getLogger(TodoFactory.class).severe(rwde.getMessage());
        }
        throw new ParseTaskFailedException(TypeEnum.TODO.toString());
    }

    public static DeadlineArguments parseDeadlineArguments(String description,
            String due, String priority, String recurrence) throws ParseTaskFailedException {
        try {
            Date dueDate = getDate(due);
            PriorityEnum priorityEnum = getPriorityEnum(priority);
            RecurrenceEnum recurrenceEnum = getRecurrenceEnum(recurrence);

            return new DeadlineArguments(description, dueDate, priorityEnum, recurrenceEnum);
        } catch (InvalidPriorityException ipe) {
            Log.getLogger(TodoFactory.class).severe(ipe.getMessage());
        } catch (ParseException pe) {
            Log.getLogger(TodoFactory.class).severe(pe.getMessage());
        } catch (InvalidRecurrenceException ire) {
            Log.getLogger(TodoFactory.class).severe(ire.getMessage());
        }
        throw new ParseTaskFailedException(TypeEnum.DEADLINE.toString());
    }

    public static EventArguments parseEventArguments(String description,
            String start, String end, String priority, String recurrence) throws ParseTaskFailedException {
        try {
            PriorityEnum priorityEnum = getPriorityEnum(priority);
            RecurrenceEnum recurrenceEnum = getRecurrenceEnum(recurrence);
            Date startDate = getDate(start);
            Date endDate = getDate(end);
            if (startDate.after(endDate)) {
                throw new StartDateAfterEndDateException();
            }
            return new EventArguments(description, startDate, endDate, priorityEnum, recurrenceEnum);
        } catch (InvalidPriorityException ipe) {
            Log.getLogger(TodoFactory.class).severe(ipe.getMessage());
        } catch (ParseException pe) {
            Log.getLogger(TodoFactory.class).severe(pe.getMessage());
        } catch (InvalidRecurrenceException ire) {
            Log.getLogger(TodoFactory.class).severe(ire.getMessage());
        } catch (StartDateAfterEndDateException sdaede) {
            Log.getLogger(TodoFactory.class).severe(sdaede.getMessage());
        }
        throw new ParseTaskFailedException(TypeEnum.EVENT.toString());
    }

    private static PriorityEnum getPriorityEnum(String priority)
            throws InvalidPriorityException {
        if (priority == null) {
            return null;
        }
        if (Utility.isInteger(priority)) {
            return PriorityEnum.getPriority(Integer.parseInt(priority));
        } else {
            return PriorityEnum.getPriority(priority);
        }
    }

    private static RecurrenceEnum getRecurrenceEnum(String recurrence)
            throws InvalidRecurrenceException {
        if (recurrence == null) {
            return null;
        }
        return RecurrenceEnum.getRecurrence(recurrence);
    }

    private static Date getDate(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        return UtilityParser.getStringAsDate(date);
    }
}
