package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class NoStoreDividerException extends ClickException {
    public NoStoreDividerException() {
        super(Messages.PRINT_NO_STORE_DIVIDER);
    }
}
