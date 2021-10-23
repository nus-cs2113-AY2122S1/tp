package seedu.duke;

import java.util.Arrays;

public class AddClientPackageCommand extends Command {
    private ClientPackage clientPackage;
    private String[] rawClientPackage;

    public AddClientPackageCommand(String [] rawClientPackage) {
        this.rawClientPackage = rawClientPackage;
    }

    @Override
    public void execute() {
        createClientPackage();
        clientPackages.add(clientPackage);
        ui.showAddClientPackage(clientPackage);
    }

    private void createClientPackage() {
        int clientIndex = stringToInt(rawClientPackage[0]) - 1;
        String tourCode = rawClientPackage[1];
        String flightId = rawClientPackage[2];
        Client client = extractClient(clientIndex);
        Tour tour = extractTour(tourCode);
        Flight flight = extractFlight(flightId);
        clientPackage = new ClientPackage(client, tour, flight);
    }

    private Client extractClient(int clientIndex) {
        return clients.getClient(clientIndex);
    }

    private Tour extractTour(String tourCode) {
        return tours.getTourByCode(tourCode);
    }

    private Flight extractFlight(String flightId) {
        return flights.getFlight(flightId);
    }

    private static int stringToInt(String params) {
        int clientIndex = Integer.parseInt(params);
        return clientIndex;
    }

}
