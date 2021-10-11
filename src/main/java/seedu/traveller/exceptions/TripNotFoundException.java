package seedu.traveller.exceptions;


public class TripNotFoundException extends TravellerException{
    public TripNotFoundException() {
        message = "The trip specified does not exist.";
    }
}
