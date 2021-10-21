package seedu.duke;

public class Package {
    private static Client client;
    private static Tour tour;
    private static Flight flight;

    public Package(Client client, Tour tour, Flight flight) {
        this.client = client;
        this.tour = tour;
        this.flight = flight;
    }
}
