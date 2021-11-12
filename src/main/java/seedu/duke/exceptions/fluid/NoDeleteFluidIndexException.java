package seedu.duke.exceptions.fluid;

//@@author pragyan01
/**
 * Custom exception for when user tries to delete a fluid entry of an invalid index.
 *
 * @author pragyan01
 */
public class NoDeleteFluidIndexException extends FluidExceptions {
    @Override
    public String getMessage() {
        return "Please enter a valid fluid index. You may wish to list to check the index numbers.";
    }
}
