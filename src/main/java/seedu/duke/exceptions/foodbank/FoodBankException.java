package seedu.duke.exceptions.foodbank;

//@@author pragyan01
public class FoodBankException extends Exception {
    @Override
    public String getMessage() {
        return "Unknown Error occurred";
    }
}
