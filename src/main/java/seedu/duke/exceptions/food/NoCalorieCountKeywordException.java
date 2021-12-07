package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class NoCalorieCountKeywordException extends ClickException {
    public NoCalorieCountKeywordException() {
        super(Messages.PRINT_NO_CALORIE_COUNT);
    }
}
