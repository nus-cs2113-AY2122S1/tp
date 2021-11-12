package seedu.duke.exceptions.meal;

//@@author VishalJeyaram
/**
 * Custom exception for when user does not key any of the details of the meal he or she wishes to add,
 * such as the description and calories.
 */
public class NoMealDetailsException extends MealException {
    @Override
    public String getMessage() {
        return "Please enter the details of the meal you wish to add!";
    }
}
