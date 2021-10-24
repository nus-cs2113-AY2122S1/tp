package seedu.traveller.exceptions;


//@@author Uxinnn
public class InvalidNumberOfDaysException extends TravellerException {
    public InvalidNumberOfDaysException(int daysNumber) {
        message = "\tThe number of days specified '" + daysNumber + "' should not be negative.";
    }

    public InvalidNumberOfDaysException(String rawDaysNumber) {
        message = "\tThe number of days specified '" + rawDaysNumber + "' is not a valid integer.";
    }
}
