package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class DuplicateNoteException extends ClickException {
    public DuplicateNoteException() {
        super("Error ! The notebook already exists");
    }
}
