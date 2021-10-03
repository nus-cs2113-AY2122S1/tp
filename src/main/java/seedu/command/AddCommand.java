package seedu.command;

import seedu.online.NusMods;

public class AddCommand extends Command {
    private final String moduleCode;

    public AddCommand(String moduleCode) {
        this.moduleCode = moduleCode;
    }

    public void execute() {

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
