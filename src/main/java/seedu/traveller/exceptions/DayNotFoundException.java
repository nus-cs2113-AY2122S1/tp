package seedu.traveller.exceptions;

public class DayNotFoundException extends TravellerException {
    public DayNotFoundException(int dayNumber) {
        message = "\tThe day '" + dayNumber + "' cannot be found.";
    }
}
