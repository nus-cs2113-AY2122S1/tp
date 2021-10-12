package parser;

import errors.InvalidCommand;
import inventory.Medicine;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParserTest {
    Ui ui = new Ui();
    ArrayList<Medicine> medicines = new ArrayList<>();

    @Test
    public void processCommand_validCommand_expectFalse() {
        try {
            boolean status = Parser.processCommand(ui, "LIST", medicines);
            assertFalse(status);
        } catch (InvalidCommand e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processCommand_exitCommand_expectTrue() {
        boolean status = false;
        try {
            status = Parser.processCommand(ui, "EXIT", medicines);
        } catch (InvalidCommand e) {
            assertTrue(status);
            e.printStackTrace();
        }
    }

    @Test
    public void parseCommand_oneSeparator_expectTwoParts() {
        String inputString = "list i/1";
        String[] stringParts = Parser.parseCommand(inputString);
        assertEquals(2, stringParts.length);
    }


    @Test
    public void parseParameters_twoParameters_expectTwoParts() {
        String inputString = "i/1 n/name";
        HashMap<String, String> parametersValues = Parser.parseParameters(inputString);
        assertEquals(2, parametersValues.keySet().size());
    }

    @Test
    public void parseParameters_threeParameters_expectThreeParts() {
        String inputString = "i/1 n/name p/20";
        HashMap<String, String> parametersValues = Parser.parseParameters(inputString);
        assertEquals(3, parametersValues.keySet().size());
    }
}
