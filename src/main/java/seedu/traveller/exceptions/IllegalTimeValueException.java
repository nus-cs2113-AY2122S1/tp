package seedu.traveller.exceptions;

public class IllegalTimeValueException extends TravellerException {
    public IllegalTimeValueException(String rawTime) {
        message = "\tThe time value entered '" + rawTime + "' is invalid. "
                + "\n\tPlease enter a time between 0000 to 2359 (All 4 digits are required).";
    }
}
