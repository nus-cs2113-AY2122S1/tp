package seedu.traveller.exceptions;


//@@author Uxinnn
public class TripNotFoundException extends TravellerException {
    public TripNotFoundException() {
        message = "\tThe trip specified does not exist.";
    }
}
