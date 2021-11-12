package seedu.duke.exceptions.meal;

//@@author VishalJeyaram
/**
 * Custom exception for when user does not key in the index of the meal he or she wishes to delete.
 */
public class NoDeleteMealIndexException extends MealException {
    @Override
    public String getMessage() {
        return "Please enter the index of the meal you wish to delete!";
    }
}
