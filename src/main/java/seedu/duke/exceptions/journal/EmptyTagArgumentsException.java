package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class EmptyTagArgumentsException extends ClickException {
    public EmptyTagArgumentsException() {
        super("No arguments for tag");
    }
}
