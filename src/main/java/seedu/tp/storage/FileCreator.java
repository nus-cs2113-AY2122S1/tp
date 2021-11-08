package seedu.tp.storage;

import java.io.File;
import java.io.IOException;
import seedu.tp.log.Log;

//@@author SeanRobertDH
/**
 * <code>FileCreator</code> handles anything to do with file creation.
 */
public class FileCreator {
    private static final String LIST_STORAGE_FOLDER = "data/";
    private static final String LIST_STORAGE_FILE = "tasks.dat";

    private File taskFile;
    private boolean hasCreatedFolder = true;
    private static final String IO_EXCEPTION_MESSAGE = "IO Exception occurred while trying to create "
        + "file for saving tasks: " + LIST_STORAGE_FOLDER + LIST_STORAGE_FILE;

    private static final String FILE_ALREADY_EXISTS = "File 'data' already exists in project folder."
        + " Please remove it and restart the application to be able to save tasks!";

    /**
     * Creates the files required for storing tasks if they are not already created.
     */
    public FileCreator() {
        createFolder();
        try {
            taskFile = createFile(LIST_STORAGE_FOLDER + LIST_STORAGE_FILE);
        } catch (IOException e) {
            Log.severe(IO_EXCEPTION_MESSAGE);
        }
    }

    private void createFolder() {
        File newFolder = new File(LIST_STORAGE_FOLDER);
        if (newFolder.exists() && !newFolder.isDirectory()) {
            Log.severe(FILE_ALREADY_EXISTS);
            hasCreatedFolder = false;
        }
        if (!newFolder.exists()) {
            newFolder.mkdir();
        }
    }

    private File createFile(String fileName) throws IOException {
        File file = new File(fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * Returns the {@link #taskFile} created by {@link seedu.tp.storage.FileCreator}.
     */
    public File getTaskFile() {
        return taskFile;
    }

    /**
     * Returns the absolute file path of the {@link #taskFile} created by {@link seedu.tp.storage.FileCreator}.
     */
    public String getTaskFileName() {
        return taskFile.getAbsolutePath();
    }

    /**
     * Returns whether the {@link #LIST_STORAGE_FOLDER} has been created.
     */
    public boolean hasCreatedFolder() {
        return hasCreatedFolder;
    }
}
