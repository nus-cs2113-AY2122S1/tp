package seedu.traveller.exceptions;


public class InvalidNewFormatException extends TravellerException {
    public InvalidNewFormatException() {
        message = "\tWrong format for New!\n\tCorrect format: new TRIP_NAME START END";
    }
}