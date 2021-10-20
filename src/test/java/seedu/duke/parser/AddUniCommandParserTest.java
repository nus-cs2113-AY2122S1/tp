package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

import seedu.duke.modules.ModuleList;
import seedu.duke.storage.UniversityStorage;
import seedu.duke.universities.UniversityList;


public class AddUniCommandParserTest {

    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();

    @Test
    public void test_validUniversityName_success() throws IOException {
        UniversityList universityMasterList = new UniversityList(UniversityStorage.load());
        AddUniCommandParser commandParser = new AddUniCommandParser();
        assertEquals(true, commandParser.isUniversityExist("Aalto University", universityMasterList));
        assertEquals(true, commandParser.isUniversityExist("Aarhus School of Business", universityMasterList));
        assertEquals(true, commandParser.isUniversityExist("Aarhus University", universityMasterList));
    }

    @Test
    public void test_invalidUniversityName_exceptionThrown() {
        try {
            UniversityList universityMasterList = new UniversityList(UniversityStorage.load());
            AddUniCommandParser commandParser = new AddUniCommandParser();
            commandParser.parse("non-existent university name", universityMasterList,
                    universitySelectedList, moduleSelectedList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            assertEquals("university does not exist", e.getMessage());
        }
    }

    @Test
    public void test_EmptyUniversityName_exceptionThrown() {
        try {
            AddUniCommandParser commandParser = new AddUniCommandParser();
            UniversityList universityMasterList = new UniversityList(UniversityStorage.load());
            commandParser.parse("", universityMasterList, universitySelectedList, moduleSelectedList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            assertEquals("no university given", e.getMessage());
        }
    }
}