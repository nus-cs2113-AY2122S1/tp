package seedu.budgettracker.storage.textfiletools;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.data.records.exceptions.DuplicateBudgetException;
import seedu.budgettracker.storage.Storage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WriteToTextFileTest {
    @Test
    void reloadArrayToStorage_() {
        Storage storage = new Storage();
        storage.makeStorageTextFile("./data/2019.txt");

        Hashtable<Integer, RecordList> allRecordList = new Hashtable<>();
        for (int i = 1; i <= 12; i++) {
            allRecordList.put(i, new RecordList(i));
        }

        try {
            allRecordList.get(2).addBudget(10002, false);
        } catch (DuplicateBudgetException e) {
            System.out.println("Error Adding!");
        }

        WriteToTextFile textFileWriter = new WriteToTextFile();
        textFileWriter.reloadArrayToStorage(allRecordList, "./data/2019.txt");

        ReadTextFile textFileReader = new ReadTextFile("./data/2019.txt");
        ArrayList<String> arrayListOfRawCommands = null;

        try {
            arrayListOfRawCommands = textFileReader.readTextFileToString();
        } catch (IOException e) {
            System.out.println("Error Reading!");
        }

        int sizeOfArrayList = arrayListOfRawCommands.size();

        assertEquals(12, sizeOfArrayList);
    }
}
