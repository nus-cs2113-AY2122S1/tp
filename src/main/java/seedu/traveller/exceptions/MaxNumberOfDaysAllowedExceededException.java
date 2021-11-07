package seedu.traveller.exceptions;

public class MaxNumberOfDaysAllowedExceededException extends TravellerException {
    public MaxNumberOfDaysAllowedExceededException() {
        message = "\tYou have reached the maximum number of days allowed per trip (30 days)."
                + "\n\tConsider deleting unneeded days to make more space.";
    }
}
