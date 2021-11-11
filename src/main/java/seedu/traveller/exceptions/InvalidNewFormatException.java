package seedu.traveller.exceptions;


//@@author Uxinnn
public class InvalidNewFormatException extends TravellerException {
    public InvalidNewFormatException() {
        message = "\tWrong format for New!\n\tCorrect format: new TRIP_NAME /from START /to END";
    }
}
