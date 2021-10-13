package happybit.storage;

import happybit.exception.HaBitLoadException;
import happybit.goal.GoalList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StorageTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    public void createFile_createFileThatExists_fileExists() {
        new Storage().createFile("data/habits.txt", "data");
        assertEquals("File exists", outputStreamCaptor.toString().trim());
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void importData_importEmptyFile_emptyArrayList() {
        Storage s = new Storage();
        GoalList actualOutput = null;
        GoalList expectedOutput = new GoalList();

        try {
            actualOutput = s.load();
            assertEquals(expectedOutput, actualOutput);
        } catch (HaBitLoadException e) {
            System.out.println(e.getMessage());
        }
    }
}