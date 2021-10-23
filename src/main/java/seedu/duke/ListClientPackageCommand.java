package seedu.duke;

public class ListClientPackageCommand extends Command {
    public void execute() {
        ui.showListClientPackage(clientPackages);
    }
}
