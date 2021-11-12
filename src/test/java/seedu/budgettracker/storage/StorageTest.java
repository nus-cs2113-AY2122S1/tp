package seedu.budgettracker.storage;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.BudgetTracker;
import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.data.records.exceptions.DuplicateBudgetException;
import seedu.budgettracker.storage.textfiletools.ReadTextFile;
import seedu.budgettracker.storage.textfiletools.WriteToTextFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

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

    @Test
    void loadStorage_dataFiles_dataIntegrity()  {
        Storage storage = new Storage();
        storage.makeStorageTextFile("./data/2019.txt");

        Hashtable<Integer, RecordList> allRecordList = new Hashtable<>();
        for (int i = 1; i <= 12; i++) {
            allRecordList.put(i, new RecordList(i));
        }

        try {
            allRecordList.get(10).addBudget(100);
        } catch (DuplicateBudgetException e) {
            System.out.println("ERROR!");
        }

        WriteToTextFile textFileWriter = new WriteToTextFile();
        textFileWriter.reloadArrayToStorage(allRecordList, "./data/2019.txt");

        Storage loader = new Storage();
        AllRecordList recordListNew = new AllRecordList();
        loader.loadStorage(recordListNew, "./data/2019.txt");

        assertTrue(100.00 == recordListNew.getBudget(10).getAmount());
    }
}
