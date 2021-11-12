package seedu.duke.commands.flights;

import seedu.duke.commands.Command;
import seedu.duke.data.Flight;

/**
 * Adds a flight into the database.
 */
public class AddFlightCommand extends Command {
    private final Flight flight;

    /**
     * Class constructor for AddFlightCommand.
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
     * If given flight ID already exists, the flight will not be added.
     */
    @Override
    public void execute() {
        int count = flights.getFlightCount();
        for (int i = 0; i < count; i++) {
            Flight currFlight = flights.getFlightByIndex(i);
            boolean sameId = currFlight.getId().equals(flight.getId());
            if (sameId) {
                System.out.println("ERROR: Flight ID already exists. Please try another flight ID.");
                return;
            }
            boolean sameDepartDestination =
                    currFlight.getDepartDestination().equals(flight.getDepartDestination());
            boolean sameReturnDestination =
                    currFlight.getReturnDestination().equals(flight.getReturnDestination());
            boolean sameDepartDate =
                    currFlight.getDepartDate().equals(flight.getDepartDate());
            boolean sameReturnDate =
                    currFlight.getReturnDate().equals(flight.getReturnDate());
            if (sameDepartDestination && sameReturnDestination
                    && sameDepartDate && sameReturnDate) {
                System.out.println("ERROR: Flight with same fields already exists with different ID.");
                return;
            }
        }
        flights.add(flight);
        ui.showAdd(flight);
    }
}
