package seedu.traveller.exceptions;


public class NoTripsCreatedException extends TravellerException {
    public NoTripsCreatedException() {
        message = "\tThere are no trips created yet.";
    }
}
