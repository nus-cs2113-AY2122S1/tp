package terminus.common;

public class Messages {

    public static final String MESSAGE_COMMAND_ADD = "add an item into your list.";
    public static final String MESSAGE_COMMAND_DELETE = "delete an item from your list.";
    public static final String MESSAGE_COMMAND_VIEW = "view all items or view an individual items";
    public static final String MESSAGE_COMMAND_BACK = "Returns to the parent workspace.";
    public static final String MESSAGE_COMMAND_EXIT = "Exits the program.";
    public static final String MESSAGE_COMMAND_HELP = "Prints the help page.";
    public static final String MESSAGE_COMMAND_NOTE = "Move to notes workspace.";
    public static final String MESSAGE_COMMAND_SCHEDULE = "Move to schedules workspace.";

    public static final String MESSAGE_RESPONSE_DELETE = "%s \'%s\' has been deleted!";
    public static final String MESSAGE_RESPONSE_ADD = "%s has been added!";

    public static final String ERROR_MESSAGE_TAG = "Error: ";

    public static final String LOCAL_TIME_FORMAT = "HH:mm";

    public static final String ERROR_MESSAGE_MISSING_ARGUMENTS = ERROR_MESSAGE_TAG + "Missing arguments.";
    public static final String ERROR_MESSAGE_EMPTY_CONTENTS = ERROR_MESSAGE_TAG + "Content not found.";
    public static final String ERROR_MESSAGE_INVALID_NUMBER = ERROR_MESSAGE_TAG + "Invalid numerical value provided.";
    public static final String ERROR_MESSAGE_INVALID_TIME_FORMAT = ERROR_MESSAGE_TAG + "Invalid time format.";
}
