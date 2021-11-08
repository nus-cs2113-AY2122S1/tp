package seedu.duke.commands.clients;

import seedu.duke.commands.Command;

/**
 * Lists all clients in the database.
 */
public class ListClientCommand extends Command {
    /**
     * Executes the listing of clients to the terminal.
     */
    public void execute() {
        ui.showListClient(clients);
    }
}
