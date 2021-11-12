package seedu.duke.exceptions.foodbank;

public class IncorrectLibraryAddFormatException extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please key in \"NAME_OF_FOOD /c CALORIES\" to add your food to the library!";
    }
}
