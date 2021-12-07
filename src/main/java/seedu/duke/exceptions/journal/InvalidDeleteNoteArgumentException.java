package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class InvalidDeleteNoteArgumentException extends ClickException {
    public InvalidDeleteNoteArgumentException() {
        super("Index has to be a number and greater than 1!");
    }
}
