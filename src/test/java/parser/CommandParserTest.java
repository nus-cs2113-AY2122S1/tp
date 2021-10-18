package parser;

import command.Command;
import command.ExitCommand;
import command.medicine.AddStockCommand;
import errors.InvalidCommand;
import org.junit.jupiter.api.Test;
import ui.Ui;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandParserTest {
    Ui ui = new Ui();

    @Test
    public void processCommand_exitCommand_expectExitObject() {
        try {
            Command command = CommandParser.processCommand("exit", Mode.STOCK);
            assertEquals(command.getClass(), ExitCommand.class);
        } catch (InvalidCommand e) {
            e.printStackTrace();
        }
    }

    @Test
    public void processCommand_addCommand_expectAddStockObject() {
        try {
            Command command = CommandParser.processCommand("add", Mode.STOCK);
            assertEquals(command.getClass(), AddStockCommand.class);
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
    public void changeMode_modeStock_expectModeDispense() {
        Mode mode = CommandParser.changeMode(ui, "dispense", Mode.STOCK);
        assertEquals(mode, Mode.DISPENSE);
    }
}
