package seedu.command;

import seedu.online.NusMods;
import seedu.storage.ModStorage;
import seedu.module.Module;
import seedu.ui.TextUi;

import java.io.IOException;

public class AddCommand extends Command {
    private final String moduleCode;

    public AddCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void execute() {
        Module module;
        try {
            module = NusMods.fetchModOnline(moduleCode);
        } catch (IOException e1) {
            TextUi.printNoConnectionMessage();
            try {
                module = ModStorage.loadModInfo(moduleCode);
            } catch (IOException e2) {
                TextUi.printNotFoundMessage();
            }
        }
    }

    /**
     *     public Module fetchMod(String moduleCode) throws IOException {
     *         try {
     *             return NusMods.fetchModOnline(moduleCode);
     *         } catch (IOException e) {
     *             TextUi.printNoConnectionMessage();
     *             return ModStorage.loadModInfo(moduleCode);
     *         }
     *     }
     */
}
