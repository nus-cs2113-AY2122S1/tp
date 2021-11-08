package seedu.duke.commons.core;

//@@author richwill28
public final class Message {
    // Error messages, sorted in alphabetical order.

    public static final String ERROR_COMPARING_LESSON =
            "Error: Lesson comparison result is incorrect, the data must be incorrect";

    public static final String ERROR_COMPARING_TASK =
            "Error: Lesson comparison result is incorrect, the data must be incorrect";

    public static final String ERROR_DESERIALIZING_LESSON =
            "Saved lesson data is invalid format. Ignoring the line..";

    public static final String ERROR_DESERIALIZING_MODULE =
            "Saved module data is invalid format. Ignoring the line..";

    public static final String ERROR_DESERIALIZING_TASK =
            "Saved task data is invalid format. Ignoring the line..";

    public static final String ERROR_DUPLICATE_FLAGS =
            "Duplicate Flag(s) found, please check your flags again.";

    public static final String ERROR_DUPLICATE_MODULES =
            "Sorry, you already have the module in the list.";

    public static final String ERROR_EMPTY_MEETING_LINK =
            "Sorry, no meeting link was provided.";

    public static final String ERROR_FILE_LOGGER =
            "Something went wrong with the file logger.";

    public static final String ERROR_INVALID_COMMAND =
            "Sorry, I don't understand your command. Enter \"help\" to see possible commands.";

    public static final String ERROR_INVALID_DAY =
            "Sorry, the input parameter is not a valid day of the week.";

    public static final String ERROR_INVALID_GRADE =
            "Sorry, the input grade is invalid. Kindly refer to NUS for a list of valid grades.";

    public static final String ERROR_INVALID_INDEX =
            "Sorry, the index is invalid.";

    public static final String ERROR_INVALID_NUMBER =
            "Sorry, the input parameter is not a valid number.";

    public static final String ERROR_INVALID_NUMBER_OF_PARAMS =
            "Sorry, the number of input parameters is invalid.";

    public static final String ERROR_INVALID_PRIORITY =
            "Sorry, the input parameter is not a valid priority.";

    public static final String ERROR_INVALID_TIME_FORMAT =
            "Sorry, the input time format should be HH:MM.";

    public static final String ERROR_INVALID_TIME_SEQUENCE =
            "Sorry, the starting time of a lesson needs to be before its ending time.";

    public static final String ERROR_INVALID_TITLE =
            "Sorry, the field for title cannot be blank.";

    public static final String ERROR_MISSING_FLAGS =
            "There are missing flags in the input given. Please check again.";

    public static final String ERROR_MODULE_IS_BLANK =
            "Sorry, no module code was provided.";

    public static final String ERROR_MODULE_LOAD_FAILED =
            "Sorry, there was an issue loading NUSMods module information.";

    public static final String ERROR_MODULE_NOT_FOUND =
            "Sorry, there is no module on NUSMods matching the module code you entered.";

    public static final String ERROR_MODULE_NOT_IN_LIST =
            "Sorry, the module that you specified is not in the list.";

    public static final String ERROR_RETRIEVING_DATA =
            "I can't retrieve the saved data. Creating new file..";

    public static final String ERROR_RETRIEVING_LESSON_DATA =
            "I can't retrieve the saved lesson data. Creating new file..";

    public static final String ERROR_RETRIEVING_MODULE_DATA =
            "I can't retrieve the saved module data. Creating new file..";

    public static final String ERROR_RETRIEVING_TASK_DATA =
            "I can't retrieve the saved task data. Creating new file..";

    public static final String ERROR_SAVING_DATA =
            "Sorry, something went wrong. I can't save the data.";

    public static final String ERROR_TASK_IS_ALREADY_DONE =
            "This task had already been marked as done before.";

    public static final String ERROR_UNKNOWN =
            "Sorry, something went wrong.";

    // Success messages, sorted in alphabetical order.

    public static final String SUCCESS_RETRIEVING_LESSON_DATA =
            "Successfully retrieved lesson data from the save file.";

    public static final String SUCCESS_RETRIEVING_MODULE_DATA =
            "Successfully retrieved module data from the save file.";

    public static final String SUCCESS_RETRIEVING_TASK_DATA =
            "Successfully retrieved task data from the save file.";
}
