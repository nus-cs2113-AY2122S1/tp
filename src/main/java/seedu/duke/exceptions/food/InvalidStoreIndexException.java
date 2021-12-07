package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class InvalidStoreIndexException extends ClickException {
    public InvalidStoreIndexException(int storeIndex) {
        super(Messages.PRINT_INVALID_STORE + storeIndex);
    }
}
