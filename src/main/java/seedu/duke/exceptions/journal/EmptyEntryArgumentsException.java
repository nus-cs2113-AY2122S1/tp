package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class EmptyEntryArgumentsException extends ClickException {
    public EmptyEntryArgumentsException() {
        super("No arguments found. Please give notebook name and entry name");
    }
}
