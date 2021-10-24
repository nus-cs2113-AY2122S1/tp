package seedu.traveller.exceptions;


//@@author Uxinnn
public class NoTripsCreatedException extends TravellerException {
    public NoTripsCreatedException() {
        message = "\tThere are no trips created yet.";
    }
}
