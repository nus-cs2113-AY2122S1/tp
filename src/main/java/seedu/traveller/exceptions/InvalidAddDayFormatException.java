package seedu.traveller.exceptions;

public class InvalidAddDayFormatException extends TravellerException {
    @Override
    public String getMessage() {
        return "\tWrong format for Add-day! \n\t Correct format: add-day TRIP_NAME NUMBER_OF_DAYS";
    }
}
