package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class EmptyEntryNameException extends ClickException {
    public EmptyEntryNameException() {
        super("No entry name entered after 'e/' ");
    }
}