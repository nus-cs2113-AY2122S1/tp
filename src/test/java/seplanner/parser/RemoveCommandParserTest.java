package seplanner.parser;

import org.junit.jupiter.api.Test;
import seplanner.commands.RemoveModCommand;
import seplanner.commands.RemoveUniCommand;
import seplanner.modules.ModuleList;
import seplanner.storage.Storage;
import seplanner.universities.UniversityList;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RemoveCommandParserTest {

    private static Storage storage = new Storage();
    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();
    private static UniversityList universityMasterList;
    private static ModuleList moduleMasterList;

    @Test void parse_universityFlagWithValidUniversity_success() {
        String arguments = "/uni someUniversityName";
        RemoveCommandParser rcp = new RemoveCommandParser();
        try {
            moduleMasterList = new ModuleList(storage.readModuleList());
            universityMasterList = new UniversityList(storage.readUniversityList(moduleMasterList));
            assertEquals(RemoveUniCommand.class, rcp.parse(arguments, universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList));
        } catch (Exception e) {
            assertEquals("University not found.", e.getMessage());
        }
    }

    @Test void parse_moduleFlagWithValidModule_success() {
        String arguments = "/mod someModule";
        RemoveCommandParser rcp = new RemoveCommandParser();
        try {
            assertEquals(RemoveModCommand.class, rcp.parse(arguments, universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList));
        } catch (Exception e) {
            assertEquals("Module not found.", e.getMessage());
        }
    }

    @Test void parse_moduleFlagWithNoModuleCode_exceptionThrown() {
        String arguments = "/mod";
        RemoveCommandParser rcp = new RemoveCommandParser();
        assertThrows(ParseException.class, () -> rcp.parse(arguments, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }

    @Test void parse_universityFlagWithNoUniversityName_exceptionThrown() {
        String arguments = "/uni";
        RemoveCommandParser rcp = new RemoveCommandParser();
        assertThrows(ParseException.class, () -> rcp.parse(arguments, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_noArguments_exceptionThrown() {
        String arguments = "";
        RemoveCommandParser rcp = new RemoveCommandParser();
        assertThrows(ParseException.class, () -> rcp.parse(arguments, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }
}