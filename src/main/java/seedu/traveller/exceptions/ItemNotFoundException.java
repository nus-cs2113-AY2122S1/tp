package seedu.traveller.exceptions;


//@@author Uxinnn
public class ItemNotFoundException extends TravellerException {
    public ItemNotFoundException(int itemNumber) {
        message = "\tThe item '" + itemNumber + "' cannot be found.";
    }
}
