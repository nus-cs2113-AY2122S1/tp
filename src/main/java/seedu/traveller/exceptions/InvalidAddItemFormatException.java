package seedu.traveller.exceptions;


//@@author conradwee
public class InvalidAddItemFormatException extends TravellerException {
    public InvalidAddItemFormatException() {
        message = "\tWrong format for AddItem!\n\tCorrect format: "
                + "add-item TRIP_NAME /day DAY_INDEX /time TIME /name ITEM_NAME";
    }
}
