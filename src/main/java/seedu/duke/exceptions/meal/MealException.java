package seedu.duke.exceptions.meal;

//@@author VishalJeyaram
/**
 * Custom exception for all errors that occur with respect to the Meal class.
 */
public class MealException extends Exception {
    @Override
    public String getMessage() {
        return "An unknown error has occurred";
    }
}
