package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.AddmodCommand;
import seedu.duke.modules.Module;
import seedu.duke.storage.Storage;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class AddmodCommandParserTest {

    @Test
    void parse_validModuleCode_expectModuleObject() throws IOException {
        String moduleCode = "CS1231";
        AddmodCommandParser acp = new AddmodCommandParser();
        ArrayList<Module> moduleMasterList = Storage.loadModules();
        assertEquals("CS1231", acp.searchForModule(moduleCode, moduleMasterList).getModuleCode());
        assertEquals("Discrete Structures", acp.searchForModule(moduleCode, moduleMasterList).getModuleName());
        assertEquals(4, acp.searchForModule(moduleCode, moduleMasterList).getModuleCredits());
    }

    @Test
    void parse_invalidModuleCode_expectException() throws IOException {
        String moduleCode = "CS1011";
        AddmodCommandParser acp = new AddmodCommandParser();
        ArrayList<Module> moduleMasterList = Storage.loadModules();
        assertThrows(ParseException.class, ()-> acp.parse(moduleCode, moduleMasterList));
    }

    @Test
    void parse_nullInput_expectException() throws IOException {
        String moduleCode = "";
        AddmodCommandParser acp = new AddmodCommandParser();
        ArrayList<Module> moduleMasterList = Storage.loadModules();
        assertThrows(ParseException.class, ()-> acp.parse(moduleCode, moduleMasterList));
    }
}