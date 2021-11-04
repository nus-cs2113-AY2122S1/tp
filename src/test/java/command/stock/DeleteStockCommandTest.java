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

    @Test
    void deleteStockCommand_stockIdZero_expectInvalid() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", "0");
        Command command = new DeleteStockCommand(parameters);
        command.execute();
        assertEquals("Invalid stock id provided!", outContent.toString().trim());
    }

    @Test
    void deleteStockCommand_negativeStockId_expectInvalid() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", "-1");
        Command command = new DeleteStockCommand(parameters);
        command.execute();
        assertEquals("Invalid stock id provided!", outContent.toString().trim());
    }

    @Test
    void deleteStockCommand_aboveStockCount_expectInvalid() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", "10");
        Command command = new DeleteStockCommand(parameters);
        command.execute();
        assertEquals("Invalid stock id provided!", outContent.toString().trim());
    }

    @Test
    void deleteStockCommand_validStockId_expectValid() {
        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", "1");
        Command command = new DeleteStockCommand(parameters);
        command.execute();
        assertEquals("Deleted row with Stock Id: 1", outContent.toString().trim());
    }

    @Test
    void deleteStockCommand_sameStockIdTwice_expectInvalid() {
        deleteStockCommand_validStockId_expectValid();

        LinkedHashMap<String, String> parameters = new LinkedHashMap<>();
        parameters.put("i", "1");
        Command command = new DeleteStockCommand(parameters);
        command.execute();
        String expectedOutput = "Deleted row with Stock Id: 1\n" + "Invalid stock id provided!";
        assertEquals(expectedOutput, outContent.toString().trim().replace("\r", ""));
    }
}
