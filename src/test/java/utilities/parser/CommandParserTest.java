package utilities.parser;

import command.Command;
import command.ExitCommand;
import command.stock.AddStockCommand;
import errors.InvalidCommandException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utilities.ui.Ui;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author alvintan01

public class CommandParserTest {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }

    Ui ui = Ui.getInstance();
    CommandParser commandParser = new CommandParser();

    @Test
    public void processCommand_exitCommand_expectExitObject() {
        try {
            Command command = commandParser.processCommand("exit", "", Mode.STOCK);
            assertEquals(command.getClass(), ExitCommand.class);
        } catch (InvalidCommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processCommand_addCommand_expectAddStockObject() {
        try {
            Command command = commandParser.processCommand("add",
                    "n/name p/10 q/20 e/10-10-2021 d/desc m/100", Mode.STOCK);
            assertEquals(command.getClass(), AddStockCommand.class);
        } catch (InvalidCommandException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseCommand_oneSeparator_expectTwoParts() {
        String inputString = "listorder i/1";
        String[] stringParts = commandParser.parseCommand(inputString);
        assertEquals(2, stringParts.length);
    }


    @Test
    public void parseParameters_twoParameters_expectTwoParts() {
        String inputString = "i/1 n/name";
        LinkedHashMap<String, String> parametersValues = commandParser.parseParameters(inputString);
        assertEquals(2, parametersValues.keySet().size());
    }

    @Test
    public void parseParameters_threeParameters_expectThreeParts() {
        String inputString = "i/1 n/name p/20";
        LinkedHashMap<String, String> parametersValues = commandParser.parseParameters(inputString);
        assertEquals(3, parametersValues.keySet().size());
    }

    @Test
    public void changeMode_currentModeStockNewModePrescription_expectModePrescription() {
        Mode mode = commandParser.changeMode(ui, "prescription", Mode.STOCK);
        assertEquals(mode, Mode.PRESCRIPTION);
    }

    @Test
    public void changeMode_currentModeStockNewModeOrder_expectModeOrder() {
        Mode mode = commandParser.changeMode(ui, "order", Mode.STOCK);
        assertEquals(mode, Mode.ORDER);
    }

    @Test
    public void changeMode_currentModeOrderNewModeStock_expectModeStock() {
        Mode mode = commandParser.changeMode(ui, "stock", Mode.ORDER);
        assertEquals(mode, Mode.STOCK);
    }

    @Test
    public void changeMode_currentStockNewModeStock_expectModeAlreadyInStockError() {
        String expectedOutput = "Already in STOCK mode!";
        Mode mode = commandParser.changeMode(ui, "stock", Mode.STOCK);
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void changeMode_currentModeOrderNewModeOrder_expectModeAlreadyInOrderError() {
        String expectedOutput = "Already in ORDER mode!";
        Mode mode = commandParser.changeMode(ui, "order", Mode.ORDER);
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }

    @Test
    public void changeMode_currentModePrescriptionNewModePrescription_expectModeAlreadyInPrescriptionError() {
        String expectedOutput = "Already in PRESCRIPTION mode!";
        Mode mode = commandParser.changeMode(ui, "prescription", Mode.PRESCRIPTION);
        // Output stream will include \r for each line break
        assertEquals(expectedOutput, outputStream.toString().trim().replace("\r", ""));
    }
}
