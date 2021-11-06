package seedu.traveller.exceptions;


//@@author jach23
public class InvalidEditItemIndexException extends TravellerException {
    public InvalidEditItemIndexException(String rawIndex) {
        message = "\tRemember edit item index " + rawIndex
                + "must be followed by a time " +
                "\n\tor date field and needs to be a valid integer 0 or above!";
    }
}
