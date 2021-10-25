package seedu.command;

import seedu.unimods.UniMods;
import seedu.ui.TextUi;
import seedu.user.Profile;

public class CalculateCapCommand extends Command {
    public CalculateCapCommand() {
    }

    public void execute() {
        Profile currentProfile = UniMods.getProfileInUse();
        TextUi.printCap(currentProfile.getCap());
    }
}
