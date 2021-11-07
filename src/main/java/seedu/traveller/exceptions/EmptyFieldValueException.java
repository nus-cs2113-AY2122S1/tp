package seedu.traveller.exceptions;

public class EmptyFieldValueException extends TravellerException {
    public EmptyFieldValueException() {
        message = "\tAll fields must be filled up.";
    }
}
