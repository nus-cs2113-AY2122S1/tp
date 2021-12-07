package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class EmptyDeleteNoteException extends ClickException {
    public EmptyDeleteNoteException() {
        super("No arguments found for deleting notebook ");
    }
}
