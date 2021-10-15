package parser;

import errors.InvalidCommand;
import inventory.Medicine;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {
    Ui ui = new Ui();
    ArrayList<Medicine> medicines = new ArrayList<>();

    @Test
    public void processCommand_validCommand_expectExit() {
        try {
            Mode mode = CommandParser.processCommand(ui, "exit", medicines, Mode.STOCK);
            assertEquals(mode, Mode.EXIT);
        } catch (InvalidCommand e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processCommand_exitCommand_expectOrder() {
        try {
            Mode mode = CommandParser.processCommand(ui, "order", medicines, Mode.STOCK);
            assertEquals(mode, Mode.ORDER);
        } catch (InvalidCommand e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parseCommand_oneSeparator_expectTwoParts() {
        String inputString = "listorder i/1";
        String[] stringParts = CommandParser.parseCommand(inputString);
        assertEquals(2, stringParts.length);
    }


    @Test
    public void parseParameters_twoParameters_expectTwoParts() {
        String inputString = "i/1 n/name";
        LinkedHashMap<String, String> parametersValues = CommandParser.parseParameters(inputString);
        assertEquals(2, parametersValues.keySet().size());
    }

    @Test
    public void parseParameters_threeParameters_expectThreeParts() {
        String inputString = "i/1 n/name p/20";
        LinkedHashMap<String, String> parametersValues = CommandParser.parseParameters(inputString);
        assertEquals(3, parametersValues.keySet().size());
    }

    @Test
    public void changeMode_modeSTOCK_expectModeDispense(){
        Mode mode = CommandParser.changeMode(ui,"dispense", Mode.DISPENSE);
        assertEquals(mode, Mode.DISPENSE);
    }
}
