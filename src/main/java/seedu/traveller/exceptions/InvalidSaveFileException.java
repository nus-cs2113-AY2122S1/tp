package seedu.traveller.exceptions;

/**
 * This class is an exception that is thrown when the save file being read has unreadable commands in it.
 */
public class InvalidSaveFileException extends TravellerException {

    @Override
    public String getMessage() {
        return "Unable to read save file! Contents have been tampered with!";
    }
}
