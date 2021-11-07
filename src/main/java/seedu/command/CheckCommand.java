package seedu.command;

import seedu.module.Module;
import seedu.online.NusMods;
import seedu.ui.TextUi;
import seedu.user.Profile;

public class CheckCommand extends Command {

    public static final String commandSyntax = "check <MODULE_CODE>";
    public static final String commandAction = "Check whether the module's pre-requisite is met";

    private final String moduleCode;
    private final Profile profile;

    public CheckCommand(String moduleCode, Profile profile) {
        this.moduleCode = moduleCode;
        this.profile = profile;
    }

    @Override
    public void execute() {
        Module module = NusMods.fetchMod(moduleCode);
        if (module != null) {
            TextUi.printPrereqMet(profile.getRecord().isModulePrereqMet(module),module);
        }
    }
}
