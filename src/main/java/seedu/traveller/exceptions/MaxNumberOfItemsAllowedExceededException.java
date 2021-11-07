package seedu.traveller.exceptions;

public class MaxNumberOfItemsAllowedExceededException extends TravellerException {
    public MaxNumberOfItemsAllowedExceededException() {
        message = "\tYou have reached the maximum number of items allowed per day (50 items)."
                + "\n\tConsider deleting unneeded items to make more space.";
    }
}
