package seedu.duke.exceptions.foodbank;

//@@author pragyan01
public class EmptyFoodDescription extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please enter a meal/fluid description, volume for fluids,"
                + " and optionally, calories if not previously added to the library! "
                + System.lineSeparator()
                + "e.g \"add meal {DESCRIPTION} /c {CALORIES}\" OR \"add meal {DESCRIPTION}\""
                + System.lineSeparator()
                + "e.g \"add fluid {DESCRIPTION} /c {CALORIES} /v {volume}\" OR \"add fluid {DESCRIPTION}\"";
    }
}
