package seedu.traveller.exceptions;


//@@author Uxinnn
public class InvalidEditItemIndexException extends TravellerException {
    public InvalidEditItemIndexException() {
        message = "\tEdit item index needs to be an integer!";
    }
}
