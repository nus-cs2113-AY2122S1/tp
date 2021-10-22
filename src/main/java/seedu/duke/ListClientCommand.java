package seedu.duke;

public class ListClientCommand extends Command {
    public void execute() {
        ui.showListClient(clients);
    }
}
