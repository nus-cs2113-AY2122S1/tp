package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class EmptyNoteNameException extends ClickException {
    public EmptyNoteNameException() {
        super("No note name entered after 'n/' ");
    }
}