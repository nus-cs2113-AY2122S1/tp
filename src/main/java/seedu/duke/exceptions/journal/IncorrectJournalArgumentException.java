package seedu.duke.exceptions.journal;

import seedu.duke.exceptions.ClickException;

public class IncorrectJournalArgumentException extends ClickException {
    public IncorrectJournalArgumentException() {
        super("Incorrect arguments found for journal.");
    }
}
