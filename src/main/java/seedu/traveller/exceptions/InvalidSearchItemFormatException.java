package seedu.traveller.exceptions;


//@@author Uxinnn
public class InvalidSearchItemFormatException extends TravellerException {
    public InvalidSearchItemFormatException() {
        message = "\tWrong format for SearchItem!\n\tCorrect format: "
                + "search-item TRIP_NAME /day DAY_INDEX /key KEYWORD";
    }
}
