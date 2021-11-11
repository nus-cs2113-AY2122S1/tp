package seedu.traveller.exceptions;


//@@author Uxinnn
public class IllegalTripNameException extends TravellerException {
    public IllegalTripNameException(String tripName) {
        message = "\tThe tripName '" + tripName + "' cannot be used as a trip name.";
    }
}
