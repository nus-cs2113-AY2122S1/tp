package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.SearchMapCommand;
import seedu.duke.storage.Storage;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchMapCommandParserTest {

    @Test
    public void testCorrectUniversityName_success() {
        try {
            UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
            University u = new University("Aalto University", new ArrayList<>());
            SearchMapCommand s = new SearchMapCommand(u);
            assertEquals(s, new SearchMapCommandParser().parse("Aalto University", universityMasterList));
        } catch (IOException | ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_NonExistentUniversityName_exceptionThrown() {
        try {
            UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
            University u = new University("Non existent", new ArrayList<>());
            SearchMapCommand s = new SearchMapCommand(u);
            assertEquals(s, new SearchMapCommandParser().parse("Aalto University", universityMasterList));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            assertEquals("university does not exist", e.getMessage());
        }
    }

    @Test
    public void test_EmptyUniversityName_exceptionThrown() {
        try {
            UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
            University u = new University("Non existent", new ArrayList<>());
            SearchMapCommand s = new SearchMapCommand(u);
            assertEquals(s, new SearchMapCommandParser().parse("Aalto University", universityMasterList));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            assertEquals("no description given", e.getMessage());
        }
    }


}
