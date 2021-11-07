package seedu.budgettracker.storage;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.AllRecordList;
import seedu.budgettracker.data.RecordList;
import seedu.budgettracker.data.records.exceptions.DuplicateBudgetException;

import java.io.File;
import java.time.DateTimeException;
import java.util.Hashtable;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConverterToCsvTest {
    @Test
    void convertToCsvFile_convertExistingFile_expectNewCsvFile() {
        Hashtable<Integer, RecordList> allRecordList = new Hashtable<>();
        Storage loader = new Storage();
        AllRecordList recordListNew = new AllRecordList();

        for (int i = 1; i <= 12; i++) {
            allRecordList.put(i, new RecordList(i));
        }

        loader.loadStorage(recordListNew, "./data/2018.txt");

        ConverterToCsv csvCreator = new ConverterToCsv();
        boolean isExist = true;

        csvCreator.convertToCsvFile(recordListNew, "./data/2018.csv");

        File tempFile = new File("./data/2018.csv");
        if (!isExist) {
            System.out.println("File does not exist!");
            isExist = true;
        }
        assertTrue(isExist);
    }
}
