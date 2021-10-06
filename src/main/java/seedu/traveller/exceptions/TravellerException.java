package seedu.traveller.exceptions;

public class TravellerException extends Exception{
    protected String message;

    public String getMessage() {
        return message;
    }
}
