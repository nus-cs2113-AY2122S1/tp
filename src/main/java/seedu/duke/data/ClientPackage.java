package seedu.duke.data;

public class ClientPackage {
    private final String clientPackageId;
    private final Client client;
    private final Tour tour;
    private final Flight flight;

    public ClientPackage(String clientPackageId, Client client, Tour tour, Flight flight) {
        this.clientPackageId = clientPackageId;
        this.client = client;
        this.tour = tour;
        this.flight = flight;
    }

    public Client getClient() {
        return client;
    }

    public Tour getTour() {
        return tour;
    }

    public Flight getFlight() {
        return flight;
    }

    public String getId() {
        return clientPackageId;
    }

    public String storageString() {
        return "Client: " + "\n"
                + client + "\n\n"
                + "Tour: " + "\n"
                + tour + "\n\n"
                + "Flight: " + "\n"
                + flight + "\n\n"
                + "Package ID: " + clientPackageId + "\n\n";
    }

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
