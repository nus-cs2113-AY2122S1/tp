package seedu.traveller.exceptions;


//@@author Jach23
public class InvalidEditMapFormatException extends TravellerException {
    public InvalidEditMapFormatException() {
        message = "\tWrong format for Edit Map! Correct format:\n"
                + "\tedit-map /from START /to END /dist NEW_DISTANCE\n";
    }
}
