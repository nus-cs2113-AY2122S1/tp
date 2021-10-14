package seedu.duke.parser;

import java.text.ParseException;
import java.util.Date;
import seedu.duke.exception.InvalidPriorityException;
import seedu.duke.exception.InvalidRecurrenceException;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;
import seedu.duke.utility.Utility;

public class TaskParser {

    public static PriorityEnum getPriorityEnum(String priority) throws InvalidPriorityException {
        if (priority == null) {
            return null;
        }
        if (Utility.isInteger(priority)) {
            return PriorityEnum.getPriority(Integer.parseInt(priority));
        } else {
            return PriorityEnum.getPriority(priority);
        }
    }

    public static RecurrenceEnum getRecurrenceEnum(String recurrence) throws InvalidRecurrenceException {
        if (recurrence == null) {
            return null;
        }
        return RecurrenceEnum.getRecurrence(recurrence);
    }

    public static Date getDate(String date) throws ParseException {
        if (date == null) {
            return null;
        }
        return UtilityParser.getStringAsDate(date);
    }
}
