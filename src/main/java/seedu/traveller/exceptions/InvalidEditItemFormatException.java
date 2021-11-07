package seedu.traveller.exceptions;


//@@author jach23
public class InvalidEditItemFormatException extends TravellerException {
    public InvalidEditItemFormatException() {
        message = "\tWrong format for EditItem!\n\tCorrect format: "
                + "\n\tedit-item TRIP /index INDEX /day DAY /time NEW_TIME /name NEW_NAME";
    }
}
