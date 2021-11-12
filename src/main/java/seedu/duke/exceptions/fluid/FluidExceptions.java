package seedu.duke.exceptions.fluid;

//@@author pragyan01
/**
 * Custom exception for when unknown error related to fluid tracker occurs.
 * Parent exception class for fluid exceptions.
 *
 * @author pragyan01
 */
public class FluidExceptions extends Exception {
    @Override
    public String getMessage() {
        return "An unknown error has occurred in FluidTracker";
    }
}
