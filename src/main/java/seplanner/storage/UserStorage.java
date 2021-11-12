package seplanner.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//@@author madhanse
/**
 * Handles the loading of text files storing user's selected universities, mappings and modules.
 */
public class UserStorage {

    /**
     * Loads the file based on the filepath.
     * @param filepath Path of the file.
     * @return The file based on the file path.
     * @throws IOException If there is a problem accessing the file.
     */
    protected File loadFile(String filepath) throws IOException {
        File file = new File(filepath);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }
        return file;
    }
}
