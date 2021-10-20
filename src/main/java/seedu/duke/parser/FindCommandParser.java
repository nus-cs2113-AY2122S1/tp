package seedu.duke.parser;

import seedu.duke.commands.FindUniCommand;
import seedu.duke.universities.UniversityList;

import java.text.ParseException;

public class FindCommandParser {

    public FindUniCommand parse(String arguments, UniversityList universityMasterList) throws ParseException {
        String input = arguments.trim();
        if (input.length() == 0) {
            throw new ParseException("no description given", 1);
        }
        return new FindUniCommand(input, universityMasterList);
    }
}
