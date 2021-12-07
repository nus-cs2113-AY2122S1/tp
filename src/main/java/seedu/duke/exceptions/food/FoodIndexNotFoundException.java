package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

//@@author ngnigel99
public class FoodIndexNotFoundException extends ClickException {

    public FoodIndexNotFoundException() {
        super(Messages.PRINT_FOOD_INDEX_NOT_FOUND);
    }
}
