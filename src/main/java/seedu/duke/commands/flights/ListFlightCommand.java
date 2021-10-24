package seedu.duke.commands.flights;

import seedu.duke.commands.Command;

public class ListFlightCommand extends Command {
    public void execute() {
        ui.showListFlight(flights);
    }
}
