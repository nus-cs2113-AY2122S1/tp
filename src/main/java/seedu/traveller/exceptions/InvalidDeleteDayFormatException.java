package seedu.traveller.exceptions;

//@@author Cuiminjing
public class InvalidDeleteDayFormatException extends TravellerException {
    public InvalidDeleteDayFormatException() {
        message = "\tWrong format for DeleteDay!"
                + "\n\tCorrect format: delete-day TRIP_NAME /day DAY_INDEX";
    }
}
