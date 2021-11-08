package seedu.duke.commands.flights;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;

/**
 * Finds a flight based on a certain id.
 */
public class FindFlightCommand extends Command {
    private final String id;

    /**
     * Class constructor for FindFlightCommand.
     *
     * @param id id used to determine which flight to find.
     */
    public FindFlightCommand(String id) {
        this.id = id;
    }

    /**
     * Executes the finding of flight, as well as finding said flight's passengers.
     * If there are no found flights, it will be shown to the user.
     */
    public void execute() {
        try {
            ui.showFindFlight(flights, clientPackages, id);
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }
}