package seedu.duke.exceptions.foodbank;

//@@author VishalJeyaram
/**
 * Custom exception for when the user keys in a meal ot fluid that already exists within the
 * meal or fluid library.
 */
public class NoFoodIndexException extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please enter a food index!";
    }
}
