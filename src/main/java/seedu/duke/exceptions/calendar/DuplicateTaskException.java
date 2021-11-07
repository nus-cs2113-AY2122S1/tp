package seedu.duke.exceptions.calendar;

import seedu.duke.exceptions.ClickException;

/**
 * Represents exception thrown when task
 * is duplicate.
 */
public class DuplicateTaskException extends ClickException {
    public DuplicateTaskException(String message) {
        super(message);
    }
}
