package seedu.duke.commands.clients;

import seedu.duke.commands.Command;

public class ListClientCommand extends Command {
    public void execute() {
        ui.showListClient(clients);
    }
}
