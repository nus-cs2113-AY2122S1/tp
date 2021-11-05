package seedu.duke.parser;

import seedu.duke.command.flags.TaskFlag;
import seedu.duke.task.PriorityEnum;
import seedu.duke.task.RecurrenceEnum;

//@@author SeanRobertDH
public class TaskUsageParser {

    private static final String STRING_REGEX = "%s";
    private static final char ARGUMENT_SPLIT = '|';

    public static String getOptionalTaskArguments(String argumentFormat) {
        String optionalTaskArguments = String.format(argumentFormat,
            TaskFlag.PRIORITY + " " + getPrioritiesListString()) + " ";
        return optionalTaskArguments + String.format(argumentFormat,
            TaskFlag.RECURRENCE + " " + getRecurrencesListString());
    }

    public static String getPrioritiesListString() {
        String listString = STRING_REGEX;
        for (PriorityEnum priority : PriorityEnum.values()) {
            listString = String.format(listString, priority.toString() + ARGUMENT_SPLIT + STRING_REGEX);
        }
        listString = listString.replaceAll(STRING_REGEX, "");
        return listString.substring(0, listString.length() - 1);
    }

    public static String getRecurrencesListString() {
        String listString = STRING_REGEX;
        for (RecurrenceEnum recurrence : RecurrenceEnum.values()) {
            if (recurrence == RecurrenceEnum.NONE) {
                continue;
            }
            listString = String.format(listString, recurrence.toString() + ARGUMENT_SPLIT + STRING_REGEX);
        }
        listString = listString.replaceAll(STRING_REGEX, "");
        return listString.substring(0, listString.length() - 1);
    }

}
