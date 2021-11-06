package seedu.budgettracker.storage.textfiletools;

import org.junit.jupiter.api.Test;
import seedu.budgettracker.data.records.exceptions.DuplicateBudgetException;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ReadTextFileTest {
    ReadTextFile textFileReader = new ReadTextFile("./data/wrong.txt");

    @Test
    void readTextFileToString_invalidFile_expectNoRawCommand() throws IOException {
        int sizeOfArrayList = 0;

        try {
            ArrayList<String> arrayListOfRawCommands = textFileReader.readTextFileToString();
            sizeOfArrayList = arrayListOfRawCommands.size();
        } catch (NullPointerException e) {
            sizeOfArrayList = 0;
        }

        assertEquals(0, sizeOfArrayList);
    }
}
