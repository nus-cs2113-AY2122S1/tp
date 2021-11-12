package seedu.duke.exceptions.fluid;

//@@author pragyan01
/**
 * Custom exception for when user tries to delete a fluid entry but fluid array is empty.
 *
 * @author pragyan01
 */
public class DeleteEmptyFluidListException extends FluidExceptions {
    @Override
    public String getMessage() {
        return "You don't have any fluid entries to delete. You may wish to add a fluid entry first.";
    }
}
