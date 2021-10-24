package seedu.duke;

import java.util.ArrayList;

public class CutFlightCommand extends Command {
    private final String flightId;
    private Flight flight;

    /**
     * Class constructor for CutFlightCommand.
     *
     * @param flightId ID of to-be-deleted flight in the flight list
     */
    public CutFlightCommand(String flightId) {
        this.flightId = flightId;
    }

    /**
     * Executes the deletion of a specific flight from flight list, corresponding to the flightId.
     */
    @Override
    public void execute() {
        try {
            cutFlight();
            cutFlightPackage();
        } catch (IndexOutOfBoundsException e1) {
            System.out.println("INVALID: Index out of bounds");
        } catch (TourPlannerException e2) {
            System.out.println(e2.getMessage());
        }
    }

    private void cutFlight() throws TourPlannerException {
        this.flight = flights.getFlightById(flightId);
        int newFlightCount = flights.getFlightCount() - 1;
        ui.showCut(flight);
        flights.cut(flight);
        assert newFlightCount == flights.getFlightCount();
        assert newFlightCount >= 0;
    }

    private void cutFlightPackage() {
        ArrayList<ClientPackage> clientPackagesWithFlight = clientPackages.getClientPackageByFlight(flight);
        for (ClientPackage clientPackage: clientPackagesWithFlight) {
            clientPackages.cut(clientPackage);
        }
    }
}

