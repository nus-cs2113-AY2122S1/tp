package seedu.traveller.exceptions;

import seedu.traveller.worldmap.exceptions.WorldMapException;

/**
 * This class is an exception that is thrown when the save text file doesn't exist.
 */
public class SaveDataNotFoundException extends TravellerException {
    public SaveDataNotFoundException() {
        message = "\tSave data cannot be found, is the save file under ./save/save.txt?";
    }
}
