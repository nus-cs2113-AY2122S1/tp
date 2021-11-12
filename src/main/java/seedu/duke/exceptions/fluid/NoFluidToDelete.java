package seedu.duke.exceptions.fluid;

//@@author pragyan01
/**
 * Custom exception for when user tries to delete a fluid entry which does not exist.
 *
 * @author pragyan01
 */
public class NoFluidToDelete extends FluidExceptions {
    @Override
    public String getMessage() {
        return "This fluid entry does not exist. You may try again or wish to add a fluid entry first.";
    }
}
