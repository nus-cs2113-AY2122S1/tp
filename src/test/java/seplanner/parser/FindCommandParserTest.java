package seplanner.parser;

import org.junit.jupiter.api.Test;

import seplanner.commands.Command;
import seplanner.commands.FindModCommand;
import seplanner.commands.SearchMapCommand;
import seplanner.exceptions.AddParseException;
import seplanner.exceptions.FindParseException;
import seplanner.exceptions.SearchMapParseException;
import seplanner.modules.Module;
import seplanner.modules.ModuleList;
import seplanner.storage.Storage;
import seplanner.universities.University;
import seplanner.universities.UniversityList;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

//@@author titustortoiseturtle1999
public class FindCommandParserTest {
    private static Storage storage = new Storage();
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
    public void test_MissingArguments_exceptionThrown() {
        try {
            new FindCommandParser().parse("find", universityMasterList, moduleMasterList);
        } catch (FindParseException e) {
            assertEquals("Missing arguments.", e.getMessage());
        }
    }

    @Test
    public void test_IncorrectFlags_exceptionThrown() {
        try {
            new FindCommandParser().parse("find /modname", universityMasterList, moduleMasterList);
        } catch (FindParseException e) {
            assertEquals("Incorrect flags passed.", e.getMessage());
        }
    }

    @Test
    public void test_MissingFlags_exceptionThrown() {
        try {
            new FindCommandParser().parse("find", universityMasterList, moduleMasterList);
        } catch (FindParseException e) {
            assertEquals("Missing arguments.", e.getMessage());
        }
    }





}
