package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.modules.ModuleList;
import seedu.duke.storage.ModuleStorage;
import seedu.duke.storage.UniversityStorage;
import seedu.duke.universities.UniversityList;


public class AddUniCommandParserTest {

    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();
    private static UniversityList universityMasterList;
    private static ModuleList moduleMasterList;

    @Test
    public void test_validUniversityName_success() throws IOException {
        moduleMasterList = new ModuleList(ModuleStorage.load());
        universityMasterList = new UniversityList(UniversityStorage.load());
        AddCommandParser commandParser = new AddCommandParser();
        assertEquals(true, commandParser.isUniversityExist("Aarhus School of Business", universityMasterList));
        assertEquals(true, commandParser.isUniversityExist("Aarhus University", universityMasterList));
    }

    @Test
    public void test_invalidUniversityName_exceptionThrown() {
        try {
            UniversityList universityMasterList = new UniversityList(UniversityStorage.load());
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
            UniversityList universityMasterList = new UniversityList(UniversityStorage.load());
            commandParser.parse("", universityMasterList, moduleMasterList,
                    universitySelectedList, moduleSelectedList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            assertEquals("Missing arguments.", e.getMessage());
        }
    }
}