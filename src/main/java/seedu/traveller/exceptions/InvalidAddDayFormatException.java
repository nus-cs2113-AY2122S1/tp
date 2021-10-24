package seedu.traveller.exceptions;


//@@author Uxinnn
public class InvalidAddDayFormatException extends TravellerException {
    @Override
    public String getMessage() {
        return "\tWrong format for add-day! \n\tCorrect format: add-day TRIP_NAME /day NUMBER_OF_DAYS";
    }
}
