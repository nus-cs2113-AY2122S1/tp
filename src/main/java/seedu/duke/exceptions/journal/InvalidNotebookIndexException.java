package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class InvalidNotebookIndexException extends ClickException {
    public InvalidNotebookIndexException() {
        super("Invalid notebook index ");
    }
}
