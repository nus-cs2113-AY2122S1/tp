package seedu.duke.commands;

import seedu.duke.modules.ModuleList;
import seedu.duke.ui.Ui;
import seedu.duke.ui.UiGeneral;
import seedu.duke.universities.UniversityList;

public class ExitCommand extends Command {
    public ExitCommand() {
        UiGeneral.printExit();
    }
}