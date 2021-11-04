package seedu.duke.exception;

/**
 * Exception to be thrown when the format of the user's input for the list command
 * {@link seedu.duke.command.SortCommand} entered by the user is incorrect.
 */
public class SortFormatException extends Exception {

    private static final String MESSAGE = "[!] Your sort command is wrong...\n"
            + "Please follow the format: sort --by [criteria]";

    @Override
    public String toString() {
        return MESSAGE;
    }

}
