package seedu.duke.exceptions.foodbank;

//@@author pragyan01
public class DuplicateFood extends FoodBankException {
    @Override
    public String getMessage() {
        return "This food entry already exists.";
    }
}
