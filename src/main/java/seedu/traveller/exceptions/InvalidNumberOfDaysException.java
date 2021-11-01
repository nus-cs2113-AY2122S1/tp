package seedu.traveller.exceptions;


//@@author Uxinnn
public class InvalidNumberOfDaysException extends TravellerException {
    public InvalidNumberOfDaysException(int daysNumber) {
        if (daysNumber == 0) {
            message = "\tThe number of days specified '" + daysNumber + "' should not be 0.";
        } else {
            message = "\tThe number of days specified '" + daysNumber + "' should not be negative.";
        }
    }

    public InvalidNumberOfDaysException(String rawDaysNumber) {
        message = "\tThe number of days specified '" + rawDaysNumber + "' is not a valid integer.";
    }
}
