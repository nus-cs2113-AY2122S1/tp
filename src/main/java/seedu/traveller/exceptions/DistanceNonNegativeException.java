package seedu.traveller.exceptions;


//@@author Jach23
public class DistanceNonNegativeException extends TravellerException {
    public DistanceNonNegativeException() {
        message = "\tDistance needs to be greater or equal to 0.1.\n"
                + "\tPlease input a valid distance.";
    }
}
