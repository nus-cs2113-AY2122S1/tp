package seedu.duke.exceptions.foodbank;

public class EmptyLibraryDescription extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please enter a food description,"
                + " and its associated calories"
                + System.lineSeparator()
                + "e.g \"library addmeal {DESCRIPTION} /c {CALORIES}\""
                + System.lineSeparator()
                + "e.g \"library addfluid {DESCRIPTION} /c {CALORIES}\"";
    }
}
