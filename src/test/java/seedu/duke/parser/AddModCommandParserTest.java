package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.ModuleStorage;
import seedu.duke.storage.UniversityStorage;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AddModCommandParserTest {

    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();
    private static UniversityList universityMasterList;
    private static ModuleList moduleMasterList;

    @Test
    void parse_validModuleCode_expectModuleObject() throws IOException {
        String moduleCode = "CS1231";
        AddCommandParser acp = new AddCommandParser();
        ModuleList moduleMasterList = new ModuleList(ModuleStorage.load());
        assertEquals("CS1231", acp.searchForModule(moduleCode, moduleMasterList).getModuleCode());
        assertEquals("Discrete Structures", acp.searchForModule(moduleCode, moduleMasterList).getModuleName());
        assertEquals(4, acp.searchForModule(moduleCode, moduleMasterList).getModuleCredits());
    }

    @Test
    void parse_invalidModuleCode_expectException() throws IOException {
        moduleMasterList = new ModuleList(ModuleStorage.load());
        universityMasterList = new UniversityList(UniversityStorage.load());
        String moduleCode = "CS1011";
        AddCommandParser acp = new AddCommandParser();
        ModuleList moduleMasterList = new ModuleList(ModuleStorage.load());
        assertThrows(ParseException.class, () -> acp.parse(moduleCode, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    void parse_nullInput_expectException() throws IOException {
        moduleMasterList = new ModuleList(ModuleStorage.load());
        universityMasterList = new UniversityList(UniversityStorage.load());
        String moduleCode = "";
        AddCommandParser acp = new AddCommandParser();
        ModuleList moduleMasterList = new ModuleList(ModuleStorage.load());
        assertThrows(ParseException.class, () -> acp.parse(moduleCode, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }
}