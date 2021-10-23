package seedu.duke;

public class ClientPackage {
    private final Client client;
    private final Tour tour;
    private final Flight flight;

    public ClientPackage(Client client, Tour tour, Flight flight, ClientPackageList clientPackages) {
//        if (clientPackages.getPackageCount() > 0) {
//            System.out.println(clientPackages.get(0));
//        }
//        if (clientPackages.getPackageCount() > 1) {
//            System.out.println(clientPackages.get(1));
//        }
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

    @Override
    public String toString() {
        return "Client: " + "\n"
                + client + "\n\n"
                + "Tour: " + "\n"
                + tour + "\n\n"
                + "Flight: " + "\n"
                + flight;
    }
}
