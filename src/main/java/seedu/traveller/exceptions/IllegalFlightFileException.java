package seedu.traveller.exceptions;

import seedu.traveller.worldmap.exceptions.WorldMapException;

public class IllegalFlightFileException extends WorldMapException{

    @Override
    public String getMessage() {
        return "My data have been tampered with. Please re-download flights.txt";
    }
}
