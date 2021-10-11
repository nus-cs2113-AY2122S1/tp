package seedu.duke.parser;

import java.text.ParseException;
import java.util.Date;
import seedu.duke.log.Log;
import seedu.duke.exception.InvalidPriorityException;
import seedu.duke.exception.InvalidRecurrenceException;
import seedu.duke.exception.PriorityNumberDoesNotExistException;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.task.factory.TodoFactory;
import seedu.duke.task.factory.arguments.DeadlineArguments;
import seedu.duke.task.factory.arguments.EventArguments;
import seedu.duke.task.factory.arguments.TodoArguments;
import seedu.duke.utility.Utility;

public class TaskParser {
    public static TodoArguments parseTodoArguments(String description,
                                                   String priority, String doOn, String recurrence) {
        try {
            final PriorityEnum priorityEnum = getPriorityEnum(priority);
            RecurrenceEnum recurrenceEnum = null;
            Date doOnDate = null;

            if (recurrence != null) {
                recurrenceEnum = RecurrenceEnum.getRecurrence(recurrence);
            }
            if (doOn != null) {
                doOnDate = UtilityParser.getStringAsDate(doOn);
            }
            return new TodoArguments(description, priorityEnum, doOnDate, recurrenceEnum);
        } catch (InvalidPriorityException ipe) {
            Log.getLogger(TodoFactory.class).severe(ipe.getMessage());
        } catch (PriorityNumberDoesNotExistException pndnee) {
            Log.getLogger(TodoFactory.class).severe(pndnee.getMessage());
        } catch (ParseException pe) {
            Log.getLogger(TodoFactory.class).severe(pe.getMessage());
        } catch (InvalidRecurrenceException ire) {
            Log.getLogger(TodoFactory.class).severe(ire.getMessage());
        }
        Utility.assertEndOfFunctionUnreachable();
        return null;
    }

    public static DeadlineArguments parseDeadlineArguments(String description,
                                                           String due, String priority, String recurrence) {
        try {
            final PriorityEnum priorityEnum = getPriorityEnum(priority);
            RecurrenceEnum recurrenceEnum = null;
            Date dueDate = null;

            if (recurrence != null) {
                recurrenceEnum = RecurrenceEnum.getRecurrence(recurrence);
            }
            if (due != null) {
                dueDate = UtilityParser.getStringAsDate(due);
            }
            return new DeadlineArguments(description, dueDate, priorityEnum, recurrenceEnum);
        } catch (InvalidPriorityException ipe) {
            Log.getLogger(TodoFactory.class).severe(ipe.getMessage());
        } catch (PriorityNumberDoesNotExistException pndnee) {
            Log.getLogger(TodoFactory.class).severe(pndnee.getMessage());
        } catch (ParseException pe) {
            Log.getLogger(TodoFactory.class).severe(pe.getMessage());
        } catch (InvalidRecurrenceException ire) {
            Log.getLogger(TodoFactory.class).severe(ire.getMessage());
        }
        Utility.assertEndOfFunctionUnreachable();
        return null;
    }

    public static EventArguments parseEventArguments(String description,
                                                     String start, String end, String priority, String recurrence) {
        try {
            final PriorityEnum priorityEnum = getPriorityEnum(priority);
            RecurrenceEnum recurrenceEnum = null;
            Date startDate = null;
            Date endDate = null;

            if (recurrence != null) {
                recurrenceEnum = RecurrenceEnum.getRecurrence(recurrence);
            }
            if (start != null) {
                startDate = UtilityParser.getStringAsDate(start);
            }
            if (end != null) {
                endDate = UtilityParser.getStringAsDate(start);
            }
            return new EventArguments(description, startDate, endDate, priorityEnum, recurrenceEnum);
        } catch (InvalidPriorityException ipe) {
            Log.getLogger(TodoFactory.class).severe(ipe.getMessage());
        } catch (PriorityNumberDoesNotExistException pndnee) {
            Log.getLogger(TodoFactory.class).severe(pndnee.getMessage());
        } catch (ParseException pe) {
            Log.getLogger(TodoFactory.class).severe(pe.getMessage());
        } catch (InvalidRecurrenceException ire) {
            Log.getLogger(TodoFactory.class).severe(ire.getMessage());
        }
        Utility.assertEndOfFunctionUnreachable();
        return null;
    }

    private static PriorityEnum getPriorityEnum(String priority)
            throws PriorityNumberDoesNotExistException, InvalidPriorityException {
        if (priority == null) {
            return null;
        }
        if (Utility.isInteger(priority)) {
            return PriorityEnum.getPriority(Integer.parseInt(priority));
        } else {
            return PriorityEnum.getPriority(priority);
        }
    }
}
