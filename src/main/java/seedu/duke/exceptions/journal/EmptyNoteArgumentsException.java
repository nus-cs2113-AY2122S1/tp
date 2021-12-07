package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class EmptyNoteArgumentsException extends ClickException {
    public EmptyNoteArgumentsException() {
        super("No arguments found for adding notebook!");
    }
}