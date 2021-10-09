package seedu.duke.parser;

import org.junit.jupiter.api.Test;
import org.w3c.dom.xpath.XPathResult;
import seedu.duke.commands.SearchMapCommand;
import seedu.duke.modules.Module;
import seedu.duke.storage.Storage;
import seedu.duke.universities.University;
import seedu.duke.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SearchMapCommandParserTest {

    @Test
    public void testCorrectUniversityName_success() throws IOException{
        try {
            UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
            assertEquals("Aalto University", new SearchMapCommandParser().parse("Aalto University", universityMasterList).getUniversityToMap().getName());
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_NonExistentUniversityName_exceptionThrown() {
        try {
            UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
            new SearchMapCommandParser().parse("non existent", universityMasterList);
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
            new SearchMapCommandParser().parse("", universityMasterList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            assertEquals("no description given", e.getMessage());
        }
    }


}
