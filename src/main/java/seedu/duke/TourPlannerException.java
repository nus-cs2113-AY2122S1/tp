package seedu.duke;

/**
 * Represents a generalised error thrown when there are erroneous/out-of-bounds/missing inputs.
 */
public class TourPlannerException extends Exception {
    /**
     * Class constructor for TourPlannerException.
     * Returns an error message to the user to inform user of error.
     *
     * @param errorMessage the customised error message corresponding to the error made
     */
    public TourPlannerException(String errorMessage) {
        super(errorMessage);
    }
}
