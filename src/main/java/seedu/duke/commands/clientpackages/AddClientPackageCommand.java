package seedu.duke.commands.clientpackages;

import seedu.duke.TourPlannerException;
import seedu.duke.commands.Command;
import seedu.duke.data.Client;
import seedu.duke.data.ClientPackage;
import seedu.duke.data.Flight;
import seedu.duke.data.Tour;

import java.util.ArrayList;

public class AddClientPackageCommand extends Command {
    public static final String ERROR_CLIENT_PACKAGE_ID_EXISTS =
            "ERROR: Package ID already exists. Please try another Package ID.";
    public static final String ERROR_CLIENT_PACKAGE_SAME_FIELDS =
            "ERROR: Package with same fields already exists with different ID.";

    private ClientPackage clientPackage;
    private String[] rawClientPackage;

    /**
     * Class constructor of AddClientPackage, used to add a ClientPackage to ClientPackageList.
     *
     * @param rawClientPackage the array of values of the client package, ordered in this manner:
     *                         package id, client id, tour id and flight id
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
            ArrayList<ClientPackage> checkClientPackage = clientPackages.getClientPackages();
            for (ClientPackage currClientPackage : checkClientPackage) {
                boolean sameId = currClientPackage.getId().equals(clientPackage.getId());
                if (sameId) {
                    System.out.println(ERROR_CLIENT_PACKAGE_ID_EXISTS);
                    return;
                }
                boolean sameClient = currClientPackage.getClient().equals(clientPackage.getClient());
                boolean sameTour = currClientPackage.getTour().equals(clientPackage.getTour());
                boolean sameFlight = currClientPackage.getFlight().equals(clientPackage.getFlight());
                if (sameClient && sameTour && sameFlight) {
                    System.out.println(ERROR_CLIENT_PACKAGE_SAME_FIELDS);
                    return;
                }
            }
            if (!clientPackage.getClient().equals(null) && !clientPackage.getTour().equals(null)
                    && !clientPackage.getFlight().equals(null)) {
                clientPackages.add(clientPackage);
                ui.showAdd(clientPackage);
            }
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Executes the addition of a ClientPackage to a ClientPackageList for Storage class.
     * If given client package ID already exists, the client package will not be added.
     */
    public void executeStorage() {
        try {
            createClientPackage();
            ArrayList<ClientPackage> checkClientPackage = clientPackages.getClientPackages();
            for (ClientPackage currClientPackage : checkClientPackage) {
                boolean sameId = currClientPackage.getId().equals(clientPackage.getId());
                if (sameId) {
                    System.out.println(ERROR_CLIENT_PACKAGE_ID_EXISTS);
                    return;
                }
                boolean sameClient = currClientPackage.getClient().equals(clientPackage.getClient());
                boolean sameTour = currClientPackage.getTour().equals(clientPackage.getTour());
                boolean sameFlight = currClientPackage.getFlight().equals(clientPackage.getFlight());
                if (sameClient && sameTour && sameFlight) {
                    System.out.println(ERROR_CLIENT_PACKAGE_SAME_FIELDS);
                    return;
                }
            }
            clientPackages.add(clientPackage);
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
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

    private void createClientPackage() throws TourPlannerException {
        String clientPackageId = rawClientPackage[0];
        String clientId = rawClientPackage[1];
        String tourId = rawClientPackage[2];
        String flightId = rawClientPackage[3];
        Client client = extractClient(clientId);
        Tour tour = extractTour(tourId);
        Flight flight = extractFlight(flightId);
        clientPackage = new ClientPackage(clientPackageId, client, tour, flight);
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
