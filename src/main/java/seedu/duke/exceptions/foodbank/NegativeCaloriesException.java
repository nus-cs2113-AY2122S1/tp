package seedu.duke.exceptions.foodbank;

public class NegativeCaloriesException extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please input a positive integer value for your calories!";
    }
}
