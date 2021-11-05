package seedu.duke.exception;

import java.util.List;

public class InvalidFlagsException extends Exception {
    private static final String MESSAGE = "Invalid flags Entered:\n%s\n";
    private static final String NEWLINE = "\n";

    public InvalidFlagsException(List<String> invalidFlags) {
        super(String.format(MESSAGE,
            String.join(NEWLINE, invalidFlags)
        ));
    }
}
