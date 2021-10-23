package seedu.traveller.exceptions;

public class InvalidFormatException extends TravellerException {

    @Override
    public String getMessage() {
        return "Wrong format! Type help to view all commands";
    }
}
