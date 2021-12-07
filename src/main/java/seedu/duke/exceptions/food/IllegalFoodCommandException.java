package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

import static seedu.duke.constants.CommandConstants.COMMAND_FOOD;

public class IllegalFoodCommandException extends ClickException {
    public IllegalFoodCommandException() {
        super(Messages.LIST_PROPER_FEATURE +  COMMAND_FOOD);
    }
}
