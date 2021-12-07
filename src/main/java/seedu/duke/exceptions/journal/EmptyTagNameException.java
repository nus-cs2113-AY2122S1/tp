package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class EmptyTagNameException extends ClickException {
    public EmptyTagNameException() {
        super("No tag name entered after 't/' ");
    }
}
