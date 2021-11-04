package seedu.duke.exception;

/**
 * Exception to be thrown when the format of the user's input for the list command
 * {@link seedu.duke.command.ListCommand} entered by the user is incorrect.
 */
public class ListFormatException extends Exception {

    private static final String INVALID_LIST_FORMAT_MESSAGE = "[!] Your list command is wrong...\n"
            + "Please follow one of the formats below:\n"
            + "-> list\n"
            + "-> list --[type, priority, recur] <argument>\n"
            + "-> list <task id>";

    @Override
    public String toString() {
        return INVALID_LIST_FORMAT_MESSAGE;
    }

}
