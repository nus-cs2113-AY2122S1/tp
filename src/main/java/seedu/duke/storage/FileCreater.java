package seedu.duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileCreater {
    public static final String LIST_STORAGE_FOLDER = "./data/";
    public static final String LIST_STORAGE_FILE = "./data/task.txt";
    public static final String DIARY_STORAGE_FILE = "./data/diary.txt";

    public static void createListFile() throws IOException {
        File newFile = new File(LIST_STORAGE_FILE);
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
    }

    public static void createFolder() {
        File newFolder = new File(LIST_STORAGE_FOLDER);
        if (!newFolder.exists()) {
            newFolder.mkdir();
        }
    }

    public static void createDiaryFile() throws IOException {
        File newFile = new File(DIARY_STORAGE_FILE);
        if (!newFile.exists()) {
            newFile.createNewFile();
        }
    }

    public static void createAll() throws IOException {
        createFolder();
        createListFile();
        createDiaryFile();
    }
}
