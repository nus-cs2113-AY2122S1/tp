package seedu.duke;

import seedu.duke.data.Client;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackage;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.Flight;
import seedu.duke.data.FlightList;
import seedu.duke.data.Tour;
import seedu.duke.data.TourList;

import java.nio.file.Path;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "TourPlanner.txt");
    private static final Path dirPath = Paths.get(root, "data");

    private static ClientList clients = new ClientList();
    private static FlightList flights = new FlightList();
    private static TourList tours = new TourList();
    private static ClientPackageList clientPackages = new ClientPackageList();

    public static ClientList getClients() {
        return clients;
    }

    public static FlightList getFlights() {
        return flights;
    }

    public static TourList getTours() {
        return tours;
    }

    public static ClientPackageList getClientPackages() {
        return clientPackages;
    }

    public Storage() throws TourPlannerException {
        try {
            File fileDirectory = new File(dirPath.toString());
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
            }

            File dataFile = new File(filePath.toString());
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new TourPlannerException("File ERROR");
        }
    }

    public void loadFile() throws TourPlannerException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);

            Client client = null;
            Tour tour = null;
            Flight flight = null;

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String clientPackageId;
                String clientId;
                String clientName;
                String clientContactNum;
                String clientEmail;
                String tourName;
                String tourCode;
                String tourPrice;
                String flightId;
                String returnDestination;
                String departDestination;
                String returnDate;
                String departDate;
                if (line.contains("Package ID")) {
                    clientPackageId = line.substring(12);
                    ClientPackage clientPackage = new ClientPackage(clientPackageId, client, tour, flight);
                    clientPackages.add(clientPackage);
                } else if (line.contains("Client")) {
                    line = scanner.nextLine();
                    clientId = line.substring(11);
                    line = scanner.nextLine();
                    clientName = line.substring(6);
                    line = scanner.nextLine();
                    clientContactNum = line.substring(16);
                    line = scanner.nextLine();
                    clientEmail = line.substring(7);

                    String[] clientDetails = {clientId, clientName, clientContactNum, clientEmail};
                    client = new Client(clientDetails);
                    clients.add(client);
                } else if (line.contains("Tour")) {
                    line = scanner.nextLine();
                    tourName = line.substring(6);
                    line = scanner.nextLine();
                    tourCode = line.substring(6);
                    line = scanner.nextLine();
                    int index = line.indexOf("$");
                    tourPrice = line.substring(index + 1);
                    String[] tourDetails = {tourCode, tourName, tourPrice};
                    tour = new Tour(tourDetails);
                    tours.add(tour);
                } else if (line.contains("Flight")) {
                    line = scanner.nextLine();
                    flightId = line.substring(11);
                    line = scanner.nextLine();
                    int index = line.indexOf(", ");
                    returnDestination = line.substring(18, index);
                    returnDate = line.substring(index + 2);
                    line = scanner.nextLine();
                    index = line.indexOf(", ");
                    departDestination = line.substring(15, index);
                    departDate = line.substring(index + 2);

                    String[] flightDetails = {flightId, departDestination, returnDestination, departDate, returnDate};
                    flight = new Flight(flightDetails);
                    flights.add(flight);
                }
            }
        } catch (FileNotFoundException e) {
            throw new TourPlannerException("File not found!");
        }
    }

    public void saveFile(ClientPackageList clientPackages) {
        try {
            FileWriter writer = new FileWriter(filePath.toString());
            ArrayList<ClientPackage> clientPackageArray = clientPackages.getClientPackages();
            for (ClientPackage clientPackage : clientPackageArray) {
                writer.write(clientPackage.storageString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
