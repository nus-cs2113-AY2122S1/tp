package seplanner.parser;

import org.junit.jupiter.api.Test;
import seplanner.commands.SearchMapCommand;
import seplanner.exceptions.SearchMapParseException;
import seplanner.modules.ModuleList;
import seplanner.storage.Storage;
import seplanner.universities.UniversityList;

import java.io.IOException;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@@author titustortoiseturtle1999
public class SearchMapCommandParserTest {

    private static Storage storage = new Storage();
    private static UniversityList universitySelectedList = new UniversityList();
    private static ModuleList moduleSelectedList = new ModuleList();
    private static ModuleList moduleMasterList;
    private static UniversityList universityMasterList;

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
    public void test_CorrectUniversityName_success() throws IOException {
        try {
            SearchMapCommand command = new SearchMapCommandParser().parse("Boston University",
                    universityMasterList, universitySelectedList, moduleSelectedList);
            assertEquals("Boston University", command.getSelectedUniversity().getName());
        } catch (SearchMapParseException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void test_NonExistentUniversityName_exceptionThrown() {
        try {
            new SearchMapCommandParser().parse("non existent", universityMasterList,
                    universitySelectedList, moduleSelectedList);
        } catch (SearchMapParseException e) {
            assertEquals("University not found.", e.getMessage());
        }
    }

    @Test
    public void test_EmptyUniversityName_exceptionThrown() {
        try {
            new SearchMapCommandParser().parse("", universityMasterList, universitySelectedList, moduleSelectedList);
        } catch (SearchMapParseException e) {
            assertEquals("Missing arguments.", e.getMessage());
        }
    }

    @Test
    public void test_IndexNotExist_exceptionThrown() {
        try {
            new SearchMapCommandParser().parse("0", universityMasterList, universitySelectedList, moduleSelectedList);
        } catch (SearchMapParseException e) {
            assertEquals("This university index does not exist.", e.getMessage());
        }
    }

    @Test
    public void test_NoAvailableMappings_exceptionThrown() {
        try {
            new SearchMapCommandParser().parse("1", universityMasterList, universitySelectedList, moduleSelectedList);
        } catch (SearchMapParseException e) {
            assertEquals("This university has no available mappings.", e.getMessage());
        }
    }
}
