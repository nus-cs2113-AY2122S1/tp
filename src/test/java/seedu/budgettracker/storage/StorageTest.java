package seedu.budgettracker.storage;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.BudgetTracker;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    @Test
    void budgetTracker_onLaunch_createNewDataFile() {
        File dataDirectory = new File("./data/");
        Storage storage = new Storage();

        storage.makeStorageTextFile("");

        File[] dataDirectoryList = dataDirectory.listFiles();
        assertTrue(dataDirectoryList.length > 0);
    }

    @Test
    void storageDirectoryListing_execute_noChangesToDataDirectory() {
        Storage newStorage = new Storage();
        int directoryFiles1 = 0;
        int directoryFiles2 = 0;
        try {
            File dataDirectory1 = new File("./data/");
            File[] dataDirectoryList1 = dataDirectory1.listFiles();
            directoryFiles1 = dataDirectoryList1.length;
        } catch (NullPointerException e) {
            directoryFiles1 = 0;
        }
        try {
            newStorage.directoryListAllFiles();
        } catch (NullPointerException e) {
            directoryFiles1 = 0;
        }

        try {
            File dataDirectory2 = new File("./data/");
            File[] dataDirectoryList2 = dataDirectory2.listFiles();
            directoryFiles2 = dataDirectoryList2.length;
        } catch (NullPointerException e) {
            directoryFiles2 = 0;
        }

        assertTrue(directoryFiles1 == directoryFiles2);
    }
}
