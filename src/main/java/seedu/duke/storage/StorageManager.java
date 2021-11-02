package seedu.duke.storage;

import seedu.duke.task.taskmanager.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class StorageManager {
    public static void readLocalData(TaskManager taskManager) {
        try {
            FileCreater.createAll();
            FileReader.initializeList(taskManager);
        } catch (IOException ioe) {
            System.out.println("Something went wrong: \n" + ioe.getMessage());
        }
    }
}
