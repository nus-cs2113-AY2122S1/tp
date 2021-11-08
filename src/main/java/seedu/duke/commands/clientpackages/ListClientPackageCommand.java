package seedu.duke.commands.clientpackages;

import seedu.duke.commands.Command;

/**
 * Lists all client packages in the database.
 */
public class ListClientPackageCommand extends Command {
    /**
     * Executes the listing of client packages to the terminal.
     */
    public void execute() {
        ui.showListClientPackage(clientPackages);
    }
}
