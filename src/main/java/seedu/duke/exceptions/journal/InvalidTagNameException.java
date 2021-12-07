package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class InvalidTagNameException extends ClickException {
    public InvalidTagNameException() {
        super("Tag name cannot be empty! ");
    }
}

