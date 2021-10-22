package seedu.duke;

public class ClientPackage {
    private static Client client;
    private static Tour tour;
    private static Flight flight;

    public ClientPackage(Client client, Tour tour, Flight flight) {
        this.client = client;
        this.tour = tour;
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Client: " + "\n"
                + client + "\n\n"
                + "Tour: " + "\n"
                + tour + "\n\n"
                + "Flight: " + "\n"
                + flight + "\n";
    }
}
