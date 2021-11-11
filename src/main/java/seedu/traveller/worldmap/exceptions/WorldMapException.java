package seedu.traveller.worldmap.exceptions;

//@@author jach23
public class WorldMapException extends Exception {
    protected String message = "An unknown error has occurred.";

    public String getMessage() {
        return message;
    }
}
