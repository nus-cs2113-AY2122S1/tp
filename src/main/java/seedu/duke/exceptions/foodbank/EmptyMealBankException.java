package seedu.duke.exceptions.foodbank;

//@@author pragyan01
public class EmptyMealBankException extends FoodBankException {
    @Override
    public String getMessage() {
        return "You don't have any meals in your library!";
    }
}
