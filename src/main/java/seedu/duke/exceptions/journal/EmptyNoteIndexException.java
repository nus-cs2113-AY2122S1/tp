package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class EmptyNoteIndexException extends ClickException {
    public EmptyNoteIndexException() {
        super("No note index entered after 'n/' ");
    }
}