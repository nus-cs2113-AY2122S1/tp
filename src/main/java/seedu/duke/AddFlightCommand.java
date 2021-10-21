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
     *
     * @param flights existing list of flights
     * @param ui      user interface of TourPlanner
     */
    @Override
    public void execute(ClientList clients, FlightList flights, TourList tours, Ui ui) {
        flights.add(flight, ui);
        ui.showAddFlight(flight);
    }
}
