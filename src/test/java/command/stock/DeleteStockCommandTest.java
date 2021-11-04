package command.stock;

import command.Command;
import command.Data;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author RemusTeo

public class DeleteStockCommandTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setup() {
        Data.generateTestData();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void restoreStreams() {
        System.setOut(originalOut);
    }

    @AfterAll
    public static void clearData() {
        Data.clearTestData();
    }

    private void executeDeleteStockCommand(String parameter, String parameterValue) {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put(parameter, parameterValue);
        Command command = new DeleteStockCommand(parameters);
        command.execute();
    }

    @Test
    void deleteStockCommand_stockIdZero_expectInvalid() {
        executeDeleteStockCommand("i", "0");
        assertEquals("Invalid stock id provided!", outContent.toString().trim());
    }

    @Test
    void deleteStockCommand_negativeStockId_expectInvalid() {
        executeDeleteStockCommand("i", "-1");
        assertEquals("Invalid stock id provided!", outContent.toString().trim());
    }

    @Test
    void deleteStockCommand_aboveStockCount_expectInvalid() {
        executeDeleteStockCommand("i", "10");
        assertEquals("Invalid stock id provided!", outContent.toString().trim());
    }

    @Test
    void deleteStockCommand_validStockId_expectValid() {
        executeDeleteStockCommand("i", "1");
        assertEquals("Deleted row with Stock Id: 1", outContent.toString().trim());
    }

    @Test
    void deleteStockCommand_sameStockIdTwice_expectInvalid() {
        deleteStockCommand_validStockId_expectValid();
        executeDeleteStockCommand("i", "1");
        String expectedOutput = "Deleted row with Stock Id: 1\n" + "Invalid stock id provided!";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }
}
