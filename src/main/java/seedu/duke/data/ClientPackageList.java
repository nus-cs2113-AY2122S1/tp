package seedu.duke.data;

import seedu.duke.TourPlannerException;

import java.util.ArrayList;

public class ClientPackageList {
    private static final String CLIENTPACKAGE_NOT_FOUND_MESSAGE
            = "Client Package cannot be found. Please try another client package ID";

    private final ArrayList<ClientPackage> clientPackages;
    private int clientPackageCount = 0;

    /**
     * Class constructor for ClientPackageList.
     */
    public ClientPackageList() {
        clientPackages = new ArrayList<>();
        clientPackageCount = 0;
    }

    /**
     * Getter for clientPackages.
     *
     * @return ArrayList containing all client packages in the database
     */
    public ArrayList<ClientPackage> getClientPackages() {
        return clientPackages;
    }

    /**
     * Getter for ClientPackage object according to its index in clientPackages.
     *
     * @param index index of the client package in clientPackages
     * @return ClientPackage object of the corresponding index
     */
    public ClientPackage getClientPackageByIndex(int index) {
        return clientPackages.get(index);
    }

    /**
     * Getter for ClientPackage object according to its clientPackageId.
     *
     * @param clientPackageId id of the client package to get from clientPackages
     * @return ClientPackage object of the corresponding client package id
     * @throws TourPlannerException if client package containing clientPackageId cannot be found in clientPackages
     */
    public ClientPackage getClientPackageById(String clientPackageId) throws TourPlannerException {
        for (int i = 0; i < clientPackageCount; i++) {
            if (clientPackages.get(i).getId().equals(clientPackageId)) {
                return clientPackages.get(i);
            }
        }
        throw new TourPlannerException(CLIENTPACKAGE_NOT_FOUND_MESSAGE);
    }

    /**
     * Getter for ClientPackage objects that contain client.
     *
     * @param client client that is contained inside the client package(s)
     * @return ArrayList of client packages that contain that client
     */
    public ArrayList<ClientPackage> getClientPackageByClient(Client client) {
        ArrayList<ClientPackage> clientPackagesWithClient = new ArrayList<>();
        for (int i = 0; i < clientPackageCount; i++) {
            ClientPackage clientPackage = clientPackages.get(i);
            if (clientPackage.getClient().equals(client)) {
                clientPackagesWithClient.add(clientPackage);
            }
        }
        return clientPackagesWithClient;
    }

    /**
     * Getter for ClientPackage objects that contain tour.
     *
     * @param tour tour that is contained inside the client package(s)
     * @return ArrayList of client packages that contain tour
     */
    public ArrayList<ClientPackage> getClientPackageByTour(Tour tour) {
        ArrayList<ClientPackage> clientPackagesWithTour = new ArrayList<>();
        for (int i = 0; i < clientPackageCount; i++) {
            ClientPackage clientPackage = clientPackages.get(i);
            if (clientPackage.getTour().equals(tour)) {
                clientPackagesWithTour.add(clientPackage);
            }
        }
        return clientPackagesWithTour;
    }

    /**
     * Getter for ClientPackage objects that contain flight.
     *
     * @param flight flight that is contained inside the client package(s)
     * @return ArrayList of client packages that contain flight
     */
    public ArrayList<ClientPackage> getClientPackageByFlight(Flight flight) {
        ArrayList<ClientPackage> clientPackagesWithFlight = new ArrayList<>();
        for (int i = 0; i < clientPackageCount; i++) {
            ClientPackage clientPackage = clientPackages.get(i);
            if (clientPackage.getFlight().equals(flight)) {
                clientPackagesWithFlight.add(clientPackage);
            }
        }
        return clientPackagesWithFlight;
    }

    /**
     * Getter for number of client packages in ClientPackageList clientPackages.
     *
     * @return clientPackageCount of clientPackages
     */
    public int getClientPackageCount() {
        return clientPackageCount;
    }

    /**
     * Adds clientPackage to clientPackages.
     *
     * @param clientPackage ClientPackage object to be added to clientPackages
     */
    public void add(ClientPackage clientPackage) {
        clientPackageCount++;
        clientPackages.add(clientPackage);
    }

    /**
     * Cuts clientPackage from clientPackages.
     *
     * @param clientPackage ClientPackage object to be deleted from clientPackages
     */
    public void cut(ClientPackage clientPackage) {
        clientPackages.remove(clientPackage);
        clientPackageCount--;
    }

}
