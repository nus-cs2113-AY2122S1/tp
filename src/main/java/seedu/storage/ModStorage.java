package seedu.storage;

import seedu.module.ModList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class ModStorage {

    private final String path;
    private final File file;

    public ModStorage(String path) {
        this.path = path;
        this.file = new File(path);
    }

    /**
     * This function attempts to create the save file at the given path if it does not already exist.
     * @return true if file is created, false otherwise
     */
    public boolean setupSave() throws FileErrorException {
        try {
            if (!file.exists()) {
                file.getParentFile().mkdirs();
            }
            if (file.createNewFile()) {
                return true;
            }
        } catch (IOException e) {
            throw new FileErrorException();
        } catch (SecurityException e) {
            throw new SecurityException();
        }
        return false;
    }

    public static boolean createModJson(String savePath) throws FileErrorException {
        try {
            File modFile = new File(savePath);
            if (!modFile.exists()) {
                modFile.getParentFile().mkdirs();
            }
            if (modFile.createNewFile()) {
                return true;
            }
        } catch (IOException e) {
            throw new FileErrorException();
        } catch (SecurityException e) {
            throw new SecurityException();
        }
        return false;
    }

    public static class FileErrorException extends Exception {
    }
}
