package seedu.command;

import seedu.duke.Duke;
import seedu.ui.TextUi;
import seedu.user.Profile;

public class CalculateCapCommand extends Command {
    public CalculateCapCommand() {
    }

    public void execute() {
        Profile currentProfile = Duke.getProfileInUse();
        TextUi.printCap(currentProfile.getCap());
    }
}
