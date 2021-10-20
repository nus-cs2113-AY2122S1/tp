package seedu.traveller.exceptions;


public class TripNotFoundException extends TravellerException {
    public TripNotFoundException() {
        message = "\tThe trip specified does not exist.";
    }
}
