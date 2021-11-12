package seedu.duke.exceptions.foodbank;

public class NoCaloriesException extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please enter the calories of your food!";
    }
}
