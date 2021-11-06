package seplanner.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import seplanner.exceptions.AddParseException;
import seplanner.modules.ModuleList;
import seplanner.storage.Storage;
import seplanner.universities.University;
import seplanner.universities.UniversityList;


public class AddUniCommandParserTest {

    private static Storage storage = new Storage();
    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();
    private static UniversityList universityMasterList;
    private static ModuleList moduleMasterList;

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
    public void test_validUniversityName_success() throws IOException {
        AddCommandParser commandParser = new AddCommandParser();
        assertEquals(true, ParseCondition.isValidUniversity(universityMasterList, "Aarhus School of Business"));
        assertEquals(true, ParseCondition.isValidUniversity(universityMasterList, "Aarhus University"));
    }

    @Test
    public void test_invalidUniversityName_exceptionThrown() {
        String input = "non-existent university name";
        AddCommandParser acp = new AddCommandParser();
        assertThrows(AddParseException.class, () -> acp.handleUniFlagArgs(input,
                universityMasterList, universitySelectedList));
    }

    @Test
    public void test_EmptyUniversityName_exceptionThrown() {
        try {
            AddCommandParser commandParser = new AddCommandParser();
            commandParser.parse("", universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            assertEquals("Missing arguments.", e.getMessage());
        }
    }

    @Test
    public void parse_universityIndexExceedLowerBound_exceptionThrown() {
        String input = "-1";
        AddCommandParser acp = new AddCommandParser();
        assertThrows(AddParseException.class, () -> acp.handleUniFlagArgs(input,
                universityMasterList, universitySelectedList));
    }

    @Test
    public void parse_universityIndexExceedUpperBound_exceptionThrown() {
        String input = "81";
        AddCommandParser acp = new AddCommandParser();
        assertThrows(AddParseException.class, () -> acp.handleUniFlagArgs(input,
                universityMasterList, universitySelectedList));
    }

    @Test
    public void parse_nonAlphabeticalOrNumericalInput_exceptionThrown() {
        String input = "@.@";
        AddCommandParser acp = new AddCommandParser();
        assertThrows(AddParseException.class, () -> acp.handleUniFlagArgs(input,
                universityMasterList, universitySelectedList));
    }

    @Test
    public void parse_addDuplicateUniversity_exceptionThrown() {
        String input = "1";
        AddCommandParser acp = new AddCommandParser();
        University dummyUniversity = new University("Aarhus School of Business", new ArrayList<>(), 0);
        universitySelectedList.addUniversity(dummyUniversity);
        assertThrows(AddParseException.class, () -> acp.handleUniFlagArgs(input,
                universityMasterList, universitySelectedList));
    }


}