package seedu.duke.commands.clientpackages;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;
import seedu.duke.data.Client;
import seedu.duke.data.ClientPackage;
import seedu.duke.data.Flight;
import seedu.duke.data.Tour;

public class AddClientPackageCommand extends Command {
    private ClientPackage clientPackage;
    private String[] rawClientPackage;

    /**
     * Class constructor of AddClientPackage, used to add a ClientPackage to ClientPackageList.
     *
     * @param rawClientPackage the array of values of the client package, ordered in this manner:
     *                          package id, client id, tour id and flight id
     */
    public AddClientPackageCommand(String[] rawClientPackage) {
        this.rawClientPackage = rawClientPackage;
    }

    /**
     * Executes the addition of a ClientPackage to a ClientPackageList.
     * If given client package ID already exists, the client package will not be added.
     */
    @Override
    public void execute() {
        try {
            createClientPackage();
            ClientPackage existingClientPackage = clientPackages.getClientPackageById(clientPackage.getId());
            System.out.println("Client package ID already exists. Please try another client package ID.");
        } catch (TourPlannerException e) {
            if (!clientPackage.getClient().equals(null) && !clientPackage.getTour().equals(null)
                    && !clientPackage.getFlight().equals(null)) {
                clientPackages.add(clientPackage);
                ui.showAdd(clientPackage);
            }
        }
    }

    /**
     * Executes the addition of a ClientPackage to a ClientPackageList for Storage class.
     * If given client package ID already exists, the client package will not be added.
     */
    public void executeStorage() {
        try {
            createClientPackage();
            ClientPackage existingClientPackage = clientPackages.getClientPackageById(clientPackage.getId());
            System.out.println("Client package ID already exists. Please try another client package ID.");
        } catch (TourPlannerException e) {
            if (!clientPackage.getClient().equals(null) && !clientPackage.getTour().equals(null)
                    && !clientPackage.getFlight().equals(null)) {
                clientPackages.add(clientPackage);
            }
        }
    }

    /**
     * Getter for the clientPackage to be added to clientPackages.
     *
     * @return Client Package that will be added to the ClientPackageList
     */
    public ClientPackage getClientPackage() {
        return clientPackage;
    }

    private void createClientPackage() {
        try {
            String clientPackageId = rawClientPackage[0];
            String clientId = rawClientPackage[1];
            String tourId = rawClientPackage[2];
            String flightId = rawClientPackage[3];
            Client client = extractClient(clientId);
            Tour tour = extractTour(tourId);
            Flight flight = extractFlight(flightId);
            clientPackage = new ClientPackage(clientPackageId, client, tour, flight);
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }

    private Client extractClient(String clientId) throws TourPlannerException {
        return clients.getClientById(clientId);
    }

    private Tour extractTour(String tourId) throws TourPlannerException {
        return tours.getTourById(tourId);
    }

    private Flight extractFlight(String flightId) throws TourPlannerException {
        return flights.getFlightById(flightId);
    }

}
