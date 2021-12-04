package seedu.duke.storage.utilities;

import seedu.duke.storage.exceptions.UnableToReadFileException;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * A File utility that checks and creates the directory and files if they are missing.
 */
public class FileChecker {

    private static final String FILEPATH = "./data/";

    /**
     * Creates the directory and file if it is unable to find the file.
     *
     * @param path The file path that it is checking
     * @throws UnableToReadFileException If it is unable to find the path specified
     */
    public static void createFileIfMissing(String path) throws IOException {
        final File file = new File(path);
        createDirectory();
        checkFileExists(file);
    }

    private static void checkFileExists(File fileToCheck) throws IOException {
        if (!fileToCheck.exists()) {
            fileToCheck.createNewFile();
        }
    }

    private static void createDirectory() throws IOException {
        Files.createDirectories(Paths.get(FILEPATH));
    }

    private static String getFileName(String path) {
        return path.split("/")[2];
    }
}
