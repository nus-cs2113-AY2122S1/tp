package seedu.duke.exceptions.fluid;

//@@author pragyan01
/**
 * Custom exception for when user tries to add a fluid entry without providing a description.
 *
 * @author pragyan01
 */
public class InvalidFluidDescription extends FluidExceptions {
    @Override
    public String getMessage() {
        return "Please enter a valid fluid description";
    }
}
