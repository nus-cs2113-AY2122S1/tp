package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import seedu.duke.commands.SearchMapCommand;
import seedu.duke.modules.ModuleList;
import seedu.duke.storage.Storage;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SearchMapCommandParserTest {

    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();

    @Test
    public void testCorrectUniversityName_success() throws IOException {
        try {
            UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
            SearchMapCommand command = new SearchMapCommandParser().parse("Aalto University",
                    universityMasterList, universitySelectedList, moduleSelectedList);
            assertEquals("Aalto University", command.getSelectedUniversity().getName());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_NonExistentUniversityName_exceptionThrown() {
        try {
            UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
            new SearchMapCommandParser().parse("non existent", universityMasterList,
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
            UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
            new SearchMapCommandParser().parse("", universityMasterList, universitySelectedList, moduleSelectedList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            assertEquals("no description given", e.getMessage());
        }
    }


}
