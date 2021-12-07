package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class NoCaloriesInputException extends ClickException {
    public NoCaloriesInputException() {
        super(Messages.PRINT_NO_INPUT_CALORIES);
    }
}
