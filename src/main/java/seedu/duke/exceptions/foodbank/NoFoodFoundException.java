package seedu.duke.exceptions.foodbank;

import seedu.duke.ClickfitMessages;

//@@author VishalJeyaram
/**
 * Custom exception for when the user keys in a meal or fluid that does not exist within the
 * meal or fluid library.
 */
public class NoFoodFoundException extends FoodBankException {
    @Override
    public String getMessage() {
        return ClickfitMessages.FOOD_BANK_EXCEPTION_MESSAGE;
    }
}
