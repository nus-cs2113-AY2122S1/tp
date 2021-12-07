package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class NotebookNotFoundForTagException extends ClickException {
    public NotebookNotFoundForTagException() {
        super("Invalid notebook for tagging");
    }
}
