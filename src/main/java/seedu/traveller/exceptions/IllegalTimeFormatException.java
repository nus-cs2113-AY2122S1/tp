package seedu.traveller.exceptions;

public class IllegalTimeFormatException extends TravellerException {
    public IllegalTimeFormatException(String rawTime) {
        message = "\tThe time entered '" + rawTime + "' cannot be recognised."
                + "\n\tPlease enter time in 24hr format."
                + "\n\tE.g. 0800";
    }
}
