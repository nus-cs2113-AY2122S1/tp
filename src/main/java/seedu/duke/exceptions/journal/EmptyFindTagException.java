package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class EmptyFindTagException extends ClickException {
    public EmptyFindTagException() {
        super("Error ! No tag name given for find!");
    }
}
