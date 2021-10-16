package seedu.traveller.exceptions;

public class InvalidItemTypeException extends TravellerException {
    public InvalidItemTypeException(String itemType) {
        message = "\tInvalid item type: " + itemType;
    }
}
