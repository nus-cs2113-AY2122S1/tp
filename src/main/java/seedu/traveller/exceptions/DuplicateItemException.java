package seedu.traveller.exceptions;

public class DuplicateItemException extends TravellerException {
    public DuplicateItemException(String itemTime, String itemName) {
        message = "\tYou already have an item named: " + itemName + " at " + itemTime + ".";
    }
}
