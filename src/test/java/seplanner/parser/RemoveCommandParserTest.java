package seplanner.parser;

import org.junit.jupiter.api.Test;
import seplanner.commands.RemoveModCommand;
import seplanner.commands.RemoveUniCommand;
import seplanner.exceptions.RemoveParseException;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.modules.ModuleMapping;
import seplanner.storage.Storage;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class RemoveCommandParserTest {

    private static Storage storage = new Storage();
    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();
    private static UniversityList universityMasterList;
    private static ModuleList moduleMasterList;

    private static final RemoveCommandParser rcp = new RemoveCommandParser();

    @Test void parse_universityFlagWithValidUniversity_success() {
        String input = "/uni someUniversityName";
        try {
            moduleMasterList = new ModuleList(storage.readModuleList());
            universityMasterList = new UniversityList(storage.readUniversityList(moduleMasterList));
            assertEquals(RemoveUniCommand.class, rcp.parse(input, universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList));
        } catch (Exception e) {
            assertEquals("This university is not in your selected list.", e.getMessage());
        }
    }

    @Test void parse_moduleFlagWithValidModule_success() {
        String input = "/mod someModule";
        try {
            assertEquals(RemoveModCommand.class, rcp.parse(input, universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList));
        } catch (Exception e) {
            assertEquals("This module is not in your selected list.", e.getMessage());
        }
    }

    @Test
    public void parse_missingArguments_exceptionThrown() {
        String noArgument = "";
        String missingUniArgument = "/uni";
        String missingModArgument = "/mod";
        String missingMapArgument = "/map";
        String missingMapArgument2 = "/map 1";
        assertThrows(RemoveParseException.class, () -> rcp.parse(noArgument, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(missingUniArgument, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(missingModArgument, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(missingMapArgument, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(missingMapArgument2, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_nonAlphabeticalOrNumericalInput_exceptionThrown() {
        String inputForUni = "/uni @.@";
        String inputForMod = "/mod >.<";
        assertThrows(RemoveParseException.class, () -> rcp.parse(inputForUni, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(inputForMod, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_inputIndexOutOfBounds_exceptionThrown() {
        String uniExceedLowerBound = "/uni 0";
        String uniExceedUpperBound = "/uni 100";
        String modExceedLowerBound = "/mod 0";
        String modExceedUpperBound = "/mod 1000";
        String mapExceedLowerBound = "/map 0 1";
        String mapExceedUpperBound = "/map 100 1";
        assertThrows(RemoveParseException.class, () -> rcp.parse(uniExceedLowerBound, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(uniExceedUpperBound, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(modExceedLowerBound, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(modExceedUpperBound, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(mapExceedLowerBound, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(mapExceedUpperBound, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_removeNotInListItem_exceptionThrown() {
        String inputByUniIndex = "/uni 1";
        String inputByUniName = "/uni Aarhus School of Business";
        String inputByModIndex = "/mod 77";
        String inputByModCode = "/mod CS1010";
        assertThrows(RemoveParseException.class, () -> rcp.parse(inputByUniIndex, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(inputByUniName, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(inputByModIndex, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(inputByModCode, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_invalidArgumentForRemoveMapping_exceptionThrown() {
        String firstInput = "/map 1 @.@";
        String secondInput = "/map >.< 1";
        String thirdInput = "/map >.< @.@";
        assertThrows(RemoveParseException.class, () -> rcp.parse(firstInput, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(secondInput, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(thirdInput, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_removeMappingForUniNotInList_exceptionThrown() {
        String input = "/map 4 1";
        // nothing is added into the uni selected list, thus uni with index 4 is not in the list
        assertThrows(RemoveParseException.class, () -> rcp.parse(input, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_removeMappingFromUniWithNoMappings_exceptionThrown() {
        String input = "/map 4 1";
        University dummyUniversity = new University("Boston University", new ArrayList<>(), 4);
        universitySelectedList.addUniversity(dummyUniversity);
        // dummy university added here has an empty list of module mappings
        assertThrows(RemoveParseException.class, () -> rcp.parse(input, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_removedMappingIndexOutOfBounds_exceptionThrown() {
        String inputExceedLowerBounds = "/map 4 0";
        String inputExceedUpperBounds = "/map 4 2";
        University dummyUniversity = new University("Boston University", new ArrayList<>(), 4);
        Module dummyModule1 = new Module("CS1010", "test", 4.0, 1);
        Module dummyModule2 = new Module("CS1234", "test", 4.0, 2);
        ModuleMapping dummyMapping = new ModuleMapping(dummyModule1, dummyModule2);
        dummyUniversity.addMapping(dummyMapping);
        universitySelectedList.addUniversity(dummyUniversity);
        // the selected lst now has a university with index 4, with only one mapping inside
        assertThrows(RemoveParseException.class, () -> rcp.parse(inputExceedLowerBounds, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        assertThrows(RemoveParseException.class, () -> rcp.parse(inputExceedUpperBounds, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

}