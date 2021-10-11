package seedu.traveller.worldmap.exceptions;


public class WorldMapException extends Exception {
    protected String message = "An unknown error has occurred.";

    public String getMessage() {
        return message;
    }
}
