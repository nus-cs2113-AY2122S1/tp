package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class EmptyJournalArgumentException extends ClickException {
    public EmptyJournalArgumentException() {
        super("No arguments found for journal.");
    }
}
