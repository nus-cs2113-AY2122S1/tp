package seedu.typists.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.typists.common.Messages.SUMMARY;

class TextUiTest {
    //This code (lines 15 to 38) was referenced from https://www.baeldung.com/java-testing-system-out-println
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void showSummary() {
        TextUi textUi = new TextUi();
        String expected = SUMMARY + '\n'+
                "Wrong Words: 5/10\n" +
                "Error Percentage: 50.00%\n"+
                "WPM: 40.08\n" +
                "Total Time taken for the game: 10.07 seconds\n";
        textUi.showSummary(5,50.00000,40.08,10,10.07);
        String actual = outputStreamCaptor.toString();
        assertEquals(expected, actual);
    }
    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}