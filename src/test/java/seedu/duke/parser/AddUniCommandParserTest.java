package seedu.duke.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

import seedu.duke.commands.*;
import seedu.duke.universities.*;
import seedu.duke.storage.Storage;


public class AddUniCommandParserTest {

    @Test
    public void AddUniCommandParser_existentUniversityName_success() throws IOException {
        UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
        AddUniCommandParser commandParser = new AddUniCommandParser();
        assertEquals(true, commandParser.isUniversityExist("Aalto University", universityMasterList));
        assertEquals(true, commandParser.isUniversityExist("Aarhus School of Business", universityMasterList));
        assertEquals(true, commandParser.isUniversityExist("Aarhus University", universityMasterList));
    }

    @Test
    public void AddUniCommandParser_nonExistentUniversityName_exceptionThrown() {
        try {
            UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
            AddUniCommandParser commandParser = new AddUniCommandParser();
            commandParser.parse("non-existent university name", universityMasterList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            assertEquals("university does not exist", e.getMessage());
        }
    }

    @Test
    public void AddUniCommandParser_EmptyUniversityName_exceptionThrown() {
        try {
            AddUniCommandParser commandParser = new AddUniCommandParser();
            UniversityList universityMasterList = new UniversityList(Storage.loadUniversities());
            commandParser.parse("", universityMasterList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            assertEquals("no university given", e.getMessage());
        }
    }
}