package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class CannotFindFoodStoreException extends ClickException {
    public CannotFindFoodStoreException(int storeIndex) {
        super(Messages.PRINT_CANNOT_FIND_STORE + storeIndex);
    }
}
