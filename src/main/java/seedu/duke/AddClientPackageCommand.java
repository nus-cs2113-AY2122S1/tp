package seedu.duke;

public class AddClientPackageCommand extends Command {
    private ClientPackage clientPackage;
    private String[] rawClientPackage;

    public AddClientPackageCommand(String[] rawClientPackage) {
        this.rawClientPackage = rawClientPackage;
    }

    @Override
    public void execute() {
        createClientPackage();
        clientPackages.add(clientPackage);
        ui.showAddClientPackage(clientPackage);
    }

    private void createClientPackage() {
        try {
            String clientId = rawClientPackage[0];
            String tourCode = rawClientPackage[1];
            String flightId = rawClientPackage[2];
            Client client = extractClient(clientId);
            Tour tour = extractTour(tourCode);
            Flight flight = extractFlight(flightId);
            clientPackage = new ClientPackage(client, tour, flight);
        } catch (TourPlannerException e) {
            System.out.println(e.getMessage());
        }
    }

    private Client extractClient(String clientId) throws TourPlannerException {
        return clients.getClientById(clientId);
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
