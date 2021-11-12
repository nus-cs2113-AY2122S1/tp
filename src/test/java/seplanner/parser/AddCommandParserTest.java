package seplanner.parser;

import org.junit.jupiter.api.Test;

import seplanner.exceptions.AddParseException;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.storage.Storage;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddCommandParserTest {

    private static Storage storage = new Storage();
    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();
    private static UniversityList universityMasterList;
    private static ModuleList moduleMasterList;

    private static final AddCommandParser acp = new AddCommandParser();

    static {
        try {
            moduleMasterList = new ModuleList(storage.readModuleList());
            universityMasterList = new UniversityList(
                    storage.readUniversityList(moduleMasterList));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void parse_invalidUniNameOrModuleCode_exceptionThrown() {
        String inputForUni = "/uni non-existent university name";
        String inputForMod = "/mod non-existent module code";
        assertThrows(AddParseException.class, () -> acp.parse(inputForUni, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
        assertThrows(AddParseException.class, () -> acp.parse(inputForMod, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_missingArguments_exceptionThrown() {
        String noUniArgument = "/uni";
        assertThrows(AddParseException.class, () -> acp.parse(noUniArgument, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
        String noModArgument = "/mod";
        assertThrows(AddParseException.class, () -> acp.parse(noModArgument, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
        String noMapArgument = "/map";
        assertThrows(AddParseException.class, () -> acp.parse(noMapArgument, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
        String missingMapArgument = "/map 1";
        assertThrows(AddParseException.class, () -> acp.parse(missingMapArgument, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_inputIndexOutOfBounds_exceptionThrown() {
        String uniExceedLowerBound = "/uni 0";
        assertThrows(AddParseException.class, () -> acp.parse(uniExceedLowerBound, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        String uniExceedUpperBound = "/uni 100";
        assertThrows(AddParseException.class, () -> acp.parse(uniExceedUpperBound, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        String modExceedLowerBound = "/mod 0";
        assertThrows(AddParseException.class, () -> acp.parse(modExceedLowerBound, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        String modExceedUpperBound = "/mod 1000";
        assertThrows(AddParseException.class, () -> acp.parse(modExceedUpperBound, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        String mapExceedLowerBound = "/map 0 1";
        assertThrows(AddParseException.class, () -> acp.parse(mapExceedLowerBound, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        String mapExceedUpperBound = "/map 100 1";
        assertThrows(AddParseException.class, () -> acp.parse(mapExceedUpperBound, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_nonAlphabeticalOrNumericalInput_exceptionThrown() {
        String inputForUni = "/uni @.@";
        assertThrows(AddParseException.class, () -> acp.parse(inputForUni, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
        String inputForMod = "/mod >.<";
        assertThrows(AddParseException.class, () -> acp.parse(inputForMod, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_addDuplicateUniversity_exceptionThrown() {
        String input = "/uni 1";
        University dummyUniversity = new University("Aarhus School of Business", new ArrayList<>(), 1);
        universitySelectedList.addUniversity(dummyUniversity);
        assertThrows(AddParseException.class, () -> acp.parse(input, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_addDuplicateModule_exceptionThrown() {
        String input = "CS1231";
        AddCommandParser acp = new AddCommandParser();
        Module dummyModule = new Module("CS1231", "test", 0, 0);
        moduleSelectedList.addModule(dummyModule);
        assertThrows(AddParseException.class, () -> acp.parse(input, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_existingInvalidArgument_exceptionThrown() {
        String firstInput = "3 ,./";
        String secondInput = ">.< 4";
        String thirdInput = "@.@ ^.^";
        assertThrows(AddParseException.class, () -> acp.parse(firstInput, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
        assertThrows(AddParseException.class, () -> acp.parse(secondInput, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
        assertThrows(AddParseException.class, () -> acp.parse(thirdInput, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_addMappingForUniNotInList_exceptionThrown() {
        String input = "/map 4 1";
        assertThrows(AddParseException.class, () -> acp.parse(input, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_addMappingForUniWithNoMapping_exceptionThrown() {
        String input = "/map 4 3";
        University dummyUniversity = new University("Boston University", new ArrayList<>(), 4);
        universitySelectedList.addUniversity(dummyUniversity);
        assertThrows(AddParseException.class, () -> acp.parse(input, universityMasterList, moduleMasterList,
                universitySelectedList, moduleSelectedList));
    }

    @Test
    public void parse_addMappingIndexOutOfBounds_exceptionThrown() {
        // this module gives exactly one mapping for Boston University (index 4)
        Module addedMod = moduleMasterList.get(116);
        moduleSelectedList.addModule(addedMod);
        University addedUni = universityMasterList.get(4);
        universityMasterList.addUniversity(addedUni);
        String inputExceedLowerBounds = "/map 4 0";
        assertThrows(AddParseException.class, () -> acp.parse(inputExceedLowerBounds, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
        String inputExceedUpperBounds = "/map 4 10";
        assertThrows(AddParseException.class, () -> acp.parse(inputExceedUpperBounds, universityMasterList,
                moduleMasterList, universitySelectedList, moduleSelectedList));
    }
}
