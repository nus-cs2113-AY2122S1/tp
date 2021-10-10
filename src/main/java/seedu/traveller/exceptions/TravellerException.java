package seedu.traveller.exceptions;

public class TravellerException extends Exception {
    protected String message;

    @Override
    public String getMessage() {
        return message;
    }
}