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
