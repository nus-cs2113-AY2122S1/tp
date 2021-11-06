package seedu.budgettracker.storage.textfiletools;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ReadTextFileTest {
    ReadTextFile textFileReader = new ReadTextFile("./data/wrong.txt");

    @Test
    void readTextFileToString_invalidFile_expectNoRawCommand() throws IOException {
        ArrayList<String> arrayListOfRawCommands = textFileReader.readTextFileToString();
        assertEquals(0, arrayListOfRawCommands.size());
    }
}
