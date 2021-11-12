package seedu.duke.exceptions.foodbank;

//@@author VishalJeyaram
/**
 * Custom exception for when the user keys in a meal index that does not exist or lies out of the
 * range of allowed meal indexes in the meal library.
 */
public class InvalidMealIndexException extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please enter a valid meal index! Use \"library listmeals\" to see the meal indexes";
    }
}
