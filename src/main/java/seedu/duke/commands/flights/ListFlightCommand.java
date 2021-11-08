package seedu.duke.commands.flights;

import seedu.duke.commands.Command;

/**
 * Lists all flights in the database.
 */
public class ListFlightCommand extends Command {
    /**
     * Executes the listing of flights to the terminal.
     */
    public void execute() {
        ui.showListFlight(flights);
    }
}
