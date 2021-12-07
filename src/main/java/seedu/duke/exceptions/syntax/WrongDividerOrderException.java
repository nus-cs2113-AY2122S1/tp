package seedu.duke.exceptions.syntax;

import seedu.duke.exceptions.ClickException;

public class WrongDividerOrderException extends ClickException {
    public WrongDividerOrderException() {
        super("Wrong divider order!");
    }
}
