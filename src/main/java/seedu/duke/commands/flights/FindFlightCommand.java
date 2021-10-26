package seedu.duke.commands.flights;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;

public class FindFlightCommand extends Command {
    private final String code;

    public FindFlightCommand(String code) {
        this.code = code;
    }

    public void execute() {
        try {
            ui.showFindFlight(flights, clientPackages, code);
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }
}