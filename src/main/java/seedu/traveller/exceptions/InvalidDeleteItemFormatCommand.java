package seedu.traveller.exceptions;


public class InvalidDeleteItemFormatCommand extends TravellerException {
    public InvalidDeleteItemFormatCommand() {
        message = "\tWrong format for DeleteItem!"
                + "\n\tCorrect format: delete-item TRIP_NAME /day DAY_INDEX /item ITEM_INDEX";
    }
}
