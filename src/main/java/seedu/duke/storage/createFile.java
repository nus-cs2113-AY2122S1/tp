package seedu.duke.storage;

import java.io.File;
import java.io.IOException;

public class createFile {
    public static final String LIST_STORAGE_FOLDER = "./data/";
    public static final String LIST_STORAGE_FILE = "./data/list.txt";
    public static final String DIARY_STORAGE_FILE = "./data/diary.txt";

    public static void createListFile() {
        File newFile = new File(LIST_STORAGE_FILE);
        if (!newFile.exists()) {
            newFile.mkdir();
        }
    }

    public static void createFolder() {
        File newFolder = new File(LIST_STORAGE_FOLDER);
        if (!newFolder.exists()) {
            newFolder.mkdir();
        }
    }

    public static void createDiaryFile() {
        File newFile = new File(DIARY_STORAGE_FILE);
        if (!newFile.exists()) {
            newFile.mkdir();
        }
    }
}
