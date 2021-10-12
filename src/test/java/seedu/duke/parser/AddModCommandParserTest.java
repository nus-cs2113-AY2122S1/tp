package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.modules.Module;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.Storage;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddModCommandParserTest {

    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();

    @Test
    void parse_validModuleCode_expectModuleObject() throws IOException {
        String moduleCode = "CS1231";
        AddModCommandParser acp = new AddModCommandParser();
        ModuleList moduleMasterList = new ModuleList(Storage.loadModules());
        assertEquals("CS1231", acp.searchForModule(moduleCode, moduleMasterList).getModuleCode());
        assertEquals("Discrete Structures", acp.searchForModule(moduleCode, moduleMasterList).getModuleName());
        assertEquals(4, acp.searchForModule(moduleCode, moduleMasterList).getModuleCredits());
    }

    @Test
    void parse_invalidModuleCode_expectException() throws IOException {
        String moduleCode = "CS1011";
        AddModCommandParser acp = new AddModCommandParser();
        ModuleList moduleMasterList = new ModuleList(Storage.loadModules());
        assertThrows(ParseException.class, () -> acp.parse(moduleCode, moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    void parse_nullInput_expectException() throws IOException {
        String moduleCode = "";
        AddModCommandParser acp = new AddModCommandParser();
        ModuleList moduleMasterList = new ModuleList(Storage.loadModules());
        assertThrows(ParseException.class, () -> acp.parse(moduleCode, moduleMasterList, universitySelectedList, moduleSelectedList));
    }
}