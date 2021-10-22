package seedu.duke;

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
        flights.add(flight, ui);
        ui.showAddFlight(flight);
    }
}
