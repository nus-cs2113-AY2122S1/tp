package seedu.duke.commands.clientpackages;

import seedu.duke.commands.Command;

public class FindClientPackageCommand extends Command {
    private final int index;

    public FindClientPackageCommand(int index) {
        this.index = index;
    }

    public void execute() {
        ui.showFindClientPackage(clientPackages, index);
    }
}
