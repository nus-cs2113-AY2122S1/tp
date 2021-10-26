package seedu.traveller.exceptions;


//@@author Uxinnn
public class InvalidEditItemFormatException extends TravellerException {
    public InvalidEditItemFormatException() {
        message = "\tWrong format for EditItem!\n\tCorrect format: "
                + "edit-item TRIP /day DAY /time NEW_TIME /name NEW_NAME /index INDEX";
    }
}
