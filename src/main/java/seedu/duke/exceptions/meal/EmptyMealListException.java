package seedu.duke.exceptions.meal;

//@@author VishalJeyaram
/**
 * Custom exception for when user attempts to delete a meal despite the meal list being empty.
 */
public class EmptyMealListException extends MealException {
    @Override
    public String getMessage() {
        return "Your meal list is empty!";
    }
}
