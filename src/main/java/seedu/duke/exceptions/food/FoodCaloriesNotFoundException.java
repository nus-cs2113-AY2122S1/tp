package seedu.duke.exceptions.food;

import seedu.duke.Click;
import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

public class FoodCaloriesNotFoundException extends ClickException {
    public FoodCaloriesNotFoundException() {
        super(Messages.CALORIES_NOT_FOUND);
    }
}
