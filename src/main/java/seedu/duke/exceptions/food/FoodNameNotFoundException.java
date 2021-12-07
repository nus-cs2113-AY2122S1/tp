package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class FoodNameNotFoundException extends ClickException {
    public FoodNameNotFoundException() {
        super(Messages.PRINT_NAME_DIVIDER);
    }
}
