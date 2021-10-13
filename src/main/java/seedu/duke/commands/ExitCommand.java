package seedu.duke.commands;

import seedu.duke.modules.ModuleList;
import seedu.duke.universities.UniversityList;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    public ExitCommand(UniversityList universitySelectedList, ModuleList moduleSelectedList) {
        super(universitySelectedList, moduleSelectedList);
    }
}