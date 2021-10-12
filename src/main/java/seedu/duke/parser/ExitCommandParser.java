package seedu.duke.parser;

import seedu.duke.commands.ExitCommand;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

public class ExitCommandParser {
    ExitCommand parse(UniversityList universitySelectedList, ModuleList moduleSelectedList) {
        return new ExitCommand(universitySelectedList, moduleSelectedList);
    }
}
