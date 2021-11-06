package seedu.duke.data;

public class ClientPackage {
    private final String clientPackageId;
    private final Client client;
    private final Tour tour;
    private final Flight flight;

    /**
     * Class constructor for ClientPackage.
     *
     * @param clientPackageId unique ID of client package to identify it by
     * @param client client to be added to the client package
     * @param tour tour to be added to the client package
     * @param flight flight to be added to the client package
     */
    public ClientPackage(String clientPackageId, Client client, Tour tour, Flight flight) {
        this.clientPackageId = clientPackageId;
        this.client = client;
        this.tour = tour;
        this.flight = flight;
    }

    /**
     * Getter for Client object of client in the client package.
     *
     * @return client in the client package
     */
    public Client getClient() {
        return client;
    }

    /**
     * Getter for Tour object of tour in the client package.
     *
     * @return tour in the client package
     */
    public Tour getTour() {
        return tour;
    }

    /**
     * Getter for Flight object of flight in the client package.
     *
     * @return flight in the client package
     */
    public Flight getFlight() {
        return flight;
    }

    /**
     * Getter for id value of ClientPackage object.
     *
     * @return id of the client package
     */
    public String getId() {
        return clientPackageId;
    }

    /**
     * Formats client package's information as a string that is viewable as an indexed list item.
     *
     * @return the formatted string containing client package's information
     */
    @Override
    public String toString() {
        return "Package ID: " + clientPackageId + "\n\n"
                + "Client: " + "\n"
                + client + "\n\n"
                + "Tour: " + "\n"
                + tour + "\n\n"
                + "Flight: " + "\n"
                + flight;
    }
}
