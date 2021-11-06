package seedu.traveller.exceptions;


//@@author jach23
public class InvalidEditItemFormatException extends TravellerException {
    public InvalidEditItemFormatException() {
        message = "\tWrong format for EditItem!\n\tCorrect format: "
                + "edit-item TRIP /index INDEX /day DAY /time NEW_TIME"
                + "\n\tOR edit-item TRIP /index INDEX /day DAY /name NEW_NAME";
    }
}
