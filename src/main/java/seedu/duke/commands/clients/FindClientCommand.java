package seedu.duke.commands.clients;

import seedu.duke.commands.Command;

public class FindClientCommand extends Command {
    private final String name;

    public FindClientCommand(String name) {
        this.name = name;
    }

    public void execute() {
        ui.showFindClient(clients, clientPackages, name);
    }
}
