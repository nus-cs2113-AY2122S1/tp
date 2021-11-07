package seedu.traveller.exceptions;


//@@author jach23
public class DistanceNonStringException extends TravellerException {
    public DistanceNonStringException() {
        message = "\tDistance needs to be a number!\n"
                + "\tPlease input a valid distance.";
    }
}
