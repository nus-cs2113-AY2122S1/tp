package seedu.duke.commands.clientpackages;

import seedu.duke.commands.Command;

public class ListClientPackageCommand extends Command {
    public void execute() {
        ui.showListClientPackage(clientPackages);
    }
}
