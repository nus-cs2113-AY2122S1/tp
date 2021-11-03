package seedu.duke.storage;

import java.io.File;
import java.io.IOException;

public class FileCreator {
    private static final String LIST_STORAGE_FOLDER = "./data/";
    private static final String LIST_STORAGE_FILE = "task.txt";

    private File taskFile;

    public FileCreator() {
        createFolder();
        try {
            taskFile = createFile(LIST_STORAGE_FOLDER + LIST_STORAGE_FILE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createFolder() {
        File newFolder = new File(LIST_STORAGE_FOLDER);
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

    public File getTaskFile() {
        return taskFile;
    }

    public String getTaskFileName() {
        return LIST_STORAGE_FOLDER + LIST_STORAGE_FILE;
    }
}
