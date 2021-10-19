package seedu.duke.exception;

public class SortFormatException extends Exception {

    private static final String MESSAGE = "[!] Your sort command is wrong...\n"
            + "Please follow the format: sort --by [criteria]";

    @Override
    public String toString() {
        return MESSAGE;
    }

}
