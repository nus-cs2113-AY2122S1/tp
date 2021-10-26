package seedu.traveller.worldmap.exceptions;

//@@author gavienwz
public class IllegalFlightFileException extends WorldMapException {

    @Override
    public String getMessage() {
        return "My data have been tampered with. Please re-download flights.txt";
    }
}
