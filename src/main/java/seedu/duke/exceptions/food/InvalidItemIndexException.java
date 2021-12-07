package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class InvalidItemIndexException extends ClickException {
    public InvalidItemIndexException(int itemIndex) {
        super(Messages.PRINT_INVALID_ITEM + itemIndex);
    }
}
