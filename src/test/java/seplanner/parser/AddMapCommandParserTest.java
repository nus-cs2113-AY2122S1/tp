package seplanner.parser;

import org.junit.jupiter.api.Test;

import seplanner.exceptions.AddParseException;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.modules.ModuleMapping;
import seplanner.storage.Storage;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.IOException;
import java.util.ArrayList;

public class AddMapCommandParserTest {

    private static Storage storage = new Storage();
    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();
    private static UniversityList universityMasterList;
    private static ModuleList moduleMasterList;

    private static AddCommandParser acp = new AddCommandParser();

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
    public void parse_inputMissingArguments_exceptionThrown() {
        String input = "3 ";
        assertThrows(AddParseException.class, () -> acp.handleMapFlagArgs(input, universitySelectedList,
                moduleSelectedList, universityMasterList));
    }

    @Test
    public void parse_existingInvalidArgument_exceptionThrown() {
        String firstInput = "3 ,./";
        String secondInput = ">.< 4";
        String thirdInput = "@.@ ^.^";
        assertThrows(AddParseException.class, () -> acp.handleMapFlagArgs(firstInput, universitySelectedList,
                moduleSelectedList, universityMasterList));
        assertThrows(AddParseException.class, () -> acp.handleMapFlagArgs(secondInput, universitySelectedList,
                moduleSelectedList, universityMasterList));
        assertThrows(AddParseException.class, () -> acp.handleMapFlagArgs(thirdInput, universitySelectedList,
                moduleSelectedList, universityMasterList));
    }

    @Test
    public void parse_chosenUniNotInSelectedList_exceptionThrown() {
        String input = "1 3";
        universitySelectedList = new UniversityList();
        assertThrows(AddParseException.class, () -> acp.handleMapFlagArgs(input, universitySelectedList,
                moduleSelectedList, universityMasterList));
    }

    @Test
    public void parse_chosenUniHasNoPotentialMapping_exceptionThrown() {
        String input = "1 1";
        moduleSelectedList = new ModuleList();
        assertThrows(AddParseException.class, () -> acp.handleMapFlagArgs(input, universitySelectedList,
                moduleSelectedList, universityMasterList));
    }

    @Test
    public void parse_uniIndexOutOfBounds_exceptionThrown() {
        String firstInput = "0 1";
        String secondInput = "81 1";
        assertThrows(AddParseException.class, () -> acp.handleMapFlagArgs(firstInput, universitySelectedList,
                moduleSelectedList, universityMasterList));
        assertThrows(AddParseException.class, () -> acp.handleMapFlagArgs(secondInput, universitySelectedList,
                moduleSelectedList, universityMasterList));
    }

    @Test
    public void parse_mappingIndexOutOfBounds_exceptionThrown() {
        String firstInput = "1 0";
        String secondInput = "1 100";
        Module dummyMod1 = new Module();
        Module dummyMod2 = new Module();
        ModuleMapping dummyMapping = new ModuleMapping(dummyMod1, dummyMod2);
        universitySelectedList.addUniversity(new University("Aarhus School of Business",
                new ArrayList<>(), 1));
        universitySelectedList.get(1).addMapping(dummyMapping);
        assertThrows(AddParseException.class, () -> acp.handleMapFlagArgs(firstInput, universitySelectedList,
                moduleSelectedList, universityMasterList));
        assertThrows(AddParseException.class, () -> acp.handleMapFlagArgs(secondInput, universitySelectedList,
                moduleSelectedList, universityMasterList));
    }


}
