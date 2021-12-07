package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class InvalidAddEntryArgumentException extends ClickException {
    public InvalidAddEntryArgumentException() {
        super("Invalid arguments for adding entry");
    }
}
