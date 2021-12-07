package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class NoFoodFoundOnDateException extends ClickException {
    public NoFoodFoundOnDateException() {
        super(Messages.PRINT_NO_FOOD_FOUND);
    }
}
