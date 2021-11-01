package seedu.traveller.exceptions;

public class EmptyFieldValueException extends TravellerException {
    public EmptyFieldValueException() {
        message = "All fields must be filled up.";
    }
}
