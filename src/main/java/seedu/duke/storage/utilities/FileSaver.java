package seedu.duke.storage.utilities;

import seedu.duke.storage.exceptions.UnableToWriteFileException;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * A File utility that saves the data into their corresponding file paths.
 */
public class FileSaver {

    /**
     * Saves an ArrayList of strings to a given filepath.
     *
     * @param path filepath of the ArrayList to be stored
     * @throws UnableToWriteFileException If the saving is interrupted by an environment variable
     */
    public static void saveTo(String path, ArrayList<String> toSave) throws UnableToWriteFileException {
        try {
            FileWriter fw = new FileWriter(path);
            writeTo(fw, toSave);
            closeFile(fw);
        } catch (IOException e) {
            throw new UnableToWriteFileException();
        }
    }

    private static void writeTo(FileWriter fw, ArrayList<String> toSave) throws IOException {
        for (String item : toSave) {
            fw.write(item + System.lineSeparator());
        }
    }

    private static void closeFile(FileWriter fw) throws IOException {
        fw.close();
    }
}
