package seedu.duke.exceptions.meal;

//@@author VishalJeyaram
/**
 * Custom exception for when the user keys in a meal index that does not exist or lies out of the
 * range of allowed meal indexes.
 */
public class NoSuchMealIndexException extends MealException {
    @Override
    public String getMessage() {
        return "Please enter a proper meal index. Use \"list meals all\" to view each meal's index";
    }
}
