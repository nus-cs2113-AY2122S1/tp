package seedu.traveller.exceptions;


//@@author Uxinnn
public class CommandNotFoundException extends TravellerException {
    public CommandNotFoundException(String userCommand) {
        message = "\tThe command '" + userCommand + "' is not recognised.";
    }
}