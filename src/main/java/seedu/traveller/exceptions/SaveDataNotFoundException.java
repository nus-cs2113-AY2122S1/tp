package seedu.traveller.exceptions;

import seedu.traveller.worldmap.exceptions.WorldMapException;

public class SaveDataNotFoundException extends WorldMapException {
    public SaveDataNotFoundException() {
        message = "\tSave data cannot be found, is the database file under ./save/save.txt?";
    }
}
