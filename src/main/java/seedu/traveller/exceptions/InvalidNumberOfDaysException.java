package seedu.traveller.exceptions;

public class InvalidNumberOfDaysException extends TravellerException {
    public InvalidNumberOfDaysException(int daysNumber) {
        message = "\tThe number of days specified '" + daysNumber + "' should not be negative.";
    }

    public InvalidNumberOfDaysException(String rawDaysNumber) {
        message = "\tThe number of days specified '" + rawDaysNumber + "' is not a valid integer.";
    }
}
