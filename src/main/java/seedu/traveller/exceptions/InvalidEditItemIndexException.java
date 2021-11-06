package seedu.traveller.exceptions;


//@@author Jach23
public class InvalidEditItemIndexException extends TravellerException {
    public InvalidEditItemIndexException(String rawIndex) {
        message = "\tEdit item index " + rawIndex + "needs to be a valid integer 0 or above!";
    }
}
