package seedu.duke.exception;

public class ListFormatException extends Exception {

    private static final String MESSAGE = "[!] Your list command is wrong...\n"
            + "Please follow the format: list or list --[type, priority, recur]";

    @Override
    public String toString() {
        return MESSAGE;
    }

}
