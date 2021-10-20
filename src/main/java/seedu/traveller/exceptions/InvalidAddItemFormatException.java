package seedu.traveller.exceptions;

public class InvalidAddItemFormatException extends TravellerException {
    public InvalidAddItemFormatException() {
        message = "\tWrong format for AddItem!\n\tCorrect format: "
                + "add-item TRIP_NAME /day DAY_INDEX /type ITEM_TYPE /name ITEM_NAME /details DETAILS";
    }
}
