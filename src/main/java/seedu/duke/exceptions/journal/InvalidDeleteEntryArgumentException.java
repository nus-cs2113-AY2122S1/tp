package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class InvalidDeleteEntryArgumentException extends ClickException {
    public InvalidDeleteEntryArgumentException() {
        super("Invalid arguments for deleting entry");
    }
}
