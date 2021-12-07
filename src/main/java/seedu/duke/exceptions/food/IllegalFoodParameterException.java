package seedu.duke.exceptions.food;

import seedu.duke.constants.Messages;
import seedu.duke.exceptions.ClickException;

//@@author ngnigel99
/**
 * Exception thrown when parameters are left blank when adding a food item.
 * More specifically, when user input is not 2 words consisting of name, calorie count.
 *
 * @author ngnigel99
 */
public class IllegalFoodParameterException extends ClickException {
    public IllegalFoodParameterException() {
        super(Messages.PRINT_ADD_FOOD_SYNTAX);
    }
}
