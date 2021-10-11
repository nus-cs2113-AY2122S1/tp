package seedu.traveller.exceptions;


public class InvalidFormatException extends TravellerException {
    public InvalidFormatException(String command) {
        switch (command) {
        case "new":
            message = "\tWrong format for New!\n\tCorrect format: new TRIP_NAME START END";
            break;
        case "edit":
            message = "\tWrong format for Edit!\n\tCorrect format: edit TRIP_NAME START END";
            break;
        default:
            message = "\tWrong format!";
            break;
        }
    }
}