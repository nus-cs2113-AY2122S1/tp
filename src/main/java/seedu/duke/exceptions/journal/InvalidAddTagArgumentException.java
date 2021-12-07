package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class InvalidAddTagArgumentException extends ClickException {
    public InvalidAddTagArgumentException() {
        super("Invalid arguments for adding tag");
    }
}
