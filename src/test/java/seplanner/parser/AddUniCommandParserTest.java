package seplanner.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seplanner.modules.ModuleList;
import seplanner.storage.Storage;
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
        try {
            AddCommandParser commandParser = new AddCommandParser();
            commandParser.parse("non-existent university name", universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            assertEquals("Incorrect flags passed.", e.getMessage());
        }
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
}