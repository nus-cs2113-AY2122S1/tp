package seedu.duke.commands.flights;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;
import seedu.duke.data.Client;
import seedu.duke.data.Flight;

/**
 * Adds a flight into the database.
 */
public class AddFlightCommand extends Command {
    private final Flight flight;

    /**
     * Class constructor for AddCommand.
     *
     * @param flight client to be added
     */
    public AddFlightCommand(Flight flight) {
        this.flight = flight;
    }

    /**
     * Returns the flight object that was added.
     *
     * @return the added flight object
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Executes the addition of flight to flight list.
     */
    @Override
    public void execute() {
        try {
            Flight existingFlight = flights.getFlightById(flight.getId());
            System.out.println("Flight ID already exists. Please try another flight ID.");
        } catch (TourPlannerException e) {
            flights.add(flight);
            ui.showAddFlight(flight);
        }
    }
}
