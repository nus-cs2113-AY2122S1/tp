package seedu.command;

import seedu.unimods.UniMods;
import seedu.exceptions.FetchException;
import seedu.exceptions.UniModsException;
import seedu.module.Module;
import seedu.online.NusMods;
import seedu.ui.TextUi;
import seedu.user.Profile;


public class RemoveCommand extends Command {
    private final String moduleToBeRemoved;

    public static final String commandSyntax = "remove <MODULE_CODE>";
    public static final String commandAction = "Removes the module from the profile's record";

    public RemoveCommand(String moduleToBeRemoved) {
        this.moduleToBeRemoved = moduleToBeRemoved;
    }

    public void execute() {
        Module module;
        try {
            module = NusMods.fetchModOnline(moduleToBeRemoved);
            Profile currentProfile = UniMods.getProfileInUse();
            currentProfile.getRecord().removeModuleFromTranscript(moduleToBeRemoved);
        } catch (FetchException e) {
            System.out.println(TextUi.ERROR_INVALID_MODULE_CODE);
        } catch (UniModsException e) {
            System.out.println(e.getMessage());
        }
    }
}
