package seedu.budgettracker.storage;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.BudgetTracker;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    @Test
    void budgetTracker_onLaunch_createNewDataFile() {
        File dataDirectory = new File("./data/");
        BudgetTracker budgetTracker = new BudgetTracker();

        File[] dataDirectoryList = dataDirectory.listFiles();
        assertTrue(dataDirectoryList.length > 0);
    }

    @Test
    void storageDirectoryListing_execute_noChangesToDataDirectory() {
        Storage newStorage = new Storage();

        File dataDirectory1 = new File("./data/");
        File[] dataDirectoryList1 = dataDirectory1.listFiles();

        newStorage.directoryListAllFiles();

        File dataDirectory2 = new File("./data/");
        File[] dataDirectoryList2 = dataDirectory2.listFiles();

        assertTrue(dataDirectoryList1.length == dataDirectoryList2.length);
    }
}
