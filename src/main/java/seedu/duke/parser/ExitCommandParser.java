package seedu.duke.parser;

import seedu.duke.commands.ExitCommand;
import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

public class ExitCommandParser {
    ExitCommand parse() {
        return new ExitCommand();
    }
}