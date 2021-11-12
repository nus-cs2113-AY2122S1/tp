package seedu.duke.exceptions.foodbank;

//@@author VishalJeyaram
/**
 * Custom exception for when the user keys in a fluid index that does not exist or lies out of the
 * range of allowed fluid indexes in the fluid library.
 */
public class InvalidFluidIndexException extends FoodBankException {
    @Override
    public String getMessage() {
        return "Please enter a valid fluid index! Use \"library listfluids\" to see the fluid indexes";
    }
}
