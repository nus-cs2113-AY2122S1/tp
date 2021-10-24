package seedu.duke.commands.flights;

import seedu.duke.commands.Command;

public class FindFlightCommand extends Command {
    private final String code;

    public FindFlightCommand(String code) {
        this.code = code;
    }

    public void execute() {
        ui.showFindFlight(flights, clientPackages, code);
    }
}