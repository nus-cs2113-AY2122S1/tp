package seedu.traveller.exceptions;


/**
 * Parent class of all exceptions that arises from using the <code>Traveller</code>.
 */
public class TravellerException extends Exception {
    protected String message;

    public String getMessage() {
        return message;
    }
}