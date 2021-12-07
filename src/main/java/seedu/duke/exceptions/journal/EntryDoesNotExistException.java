package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class EntryDoesNotExistException extends ClickException {
    public EntryDoesNotExistException() {
        super("Entry doesn't exist.");
    }
}
