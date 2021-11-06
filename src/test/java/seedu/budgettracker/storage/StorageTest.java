package seedu.budgettracker.storage;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.BudgetTracker;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class StorageTest {
    @Test
    void budgetTracker_launch_createNewDataFile() {
        File dataDirectory = new File("./data/");
        BudgetTracker budgetTracker = new BudgetTracker();

        File[] dataDirectoryList = dataDirectory.listFiles();
        assertTrue(dataDirectoryList.length > 0);
    }
}
