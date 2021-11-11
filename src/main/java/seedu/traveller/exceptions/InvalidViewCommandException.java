package seedu.traveller.exceptions;


public class InvalidViewCommandException extends TravellerException {
    public InvalidViewCommandException() {
        message = "\tWrong format for View!\n\tCorrect format: view TRIP_NAME";
    }
}
