package seedu.command;

import seedu.unimods.UniMods;
import seedu.ui.TextUi;
import seedu.user.Profile;



public class CalculateCapCommand extends Command {

    public static final String commandSyntax = "calculate";
    public static final String commandAction = "Calculates your CAP from your profile.";

    public CalculateCapCommand() {
    }

    public void execute() {
        Profile currentProfile = UniMods.getProfileInUse();
        TextUi.printCap(currentProfile.getCap());
    }
}
