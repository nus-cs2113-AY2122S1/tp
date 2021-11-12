package seedu.duke.exceptions.foodbank;

//@@author pragyan01
public class EmptyFluidBankException extends FoodBankException {
    @Override
    public String getMessage() {
        return "You don't have any fluids in your library!";
    }
}
