package seedu.duke.parser;

import seedu.duke.command.flags.TaskFlag;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;

//@@author SeanRobertDH
public class TaskUsageParser {

    private static final String STRING_REGEX = "%s";

    public static String getOptionalTaskArguments(String argumentFormat, String argumentSplit) {
        String optionalTaskArguments = String.format(argumentFormat,
            TaskFlag.PRIORITY + " " + getPrioritiesListString(argumentSplit)) + " ";
        return optionalTaskArguments + String.format(argumentFormat,
            TaskFlag.RECURRENCE + " " + getRecurrencesListString(argumentSplit));
    }

    private static String getPrioritiesListString(String argumentSplit) {
        String listString = STRING_REGEX;
        for (PriorityEnum priority : PriorityEnum.values()) {
            listString = String.format(listString, priority.toString() + argumentSplit + STRING_REGEX);
        }
        listString = listString.replaceAll(STRING_REGEX, "");
        return listString.substring(0, listString.length() - 1);
    }

    private static String getRecurrencesListString(String argumentSplit) {
        String listString = STRING_REGEX;
        for (RecurrenceEnum recurrence : RecurrenceEnum.values()) {
            if (recurrence == RecurrenceEnum.NONE) {
                continue;
            }
            listString = String.format(listString, recurrence.toString() + argumentSplit + STRING_REGEX);
        }
        listString = listString.replaceAll(STRING_REGEX, "");
        return listString.substring(0, listString.length() - 1);
    }

}
