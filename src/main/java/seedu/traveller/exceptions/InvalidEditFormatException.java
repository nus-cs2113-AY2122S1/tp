package seedu.traveller.exceptions;

public class InvalidEditFormatException extends TravellerException {
    public InvalidEditFormatException() {
        message = "\tWrong format for Edit!\n\tCorrect format: edit TRIP_NAME /name NEW_TRIP_NAME /from START /to END";
    }
}
