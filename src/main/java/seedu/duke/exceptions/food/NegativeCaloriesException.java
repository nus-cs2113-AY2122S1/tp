package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class NegativeCaloriesException extends ClickException {
    public NegativeCaloriesException() {
        super(Messages.PRINT_NON_NEGATIVE_CALORIES);
    }
}
