package seedu.traveller.exceptions;


public class InvalidSearchFormatException extends TravellerException {
    public InvalidSearchFormatException() {
        message = "\tWrong format for Search!\n\tCorrect format: search /from START /to END";
    }
}
