package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class NoItemDividerException extends ClickException {
    public NoItemDividerException() {
        super(Messages.PRINT_NO_INDEX_DIVIDER);
    }
}
