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

    private static final ClientList clients = new ClientList();
    private static final FlightList flights = new FlightList();
    private static final TourList tours = new TourList();
    private static final ClientPackageList clientPackages = new ClientPackageList();

    public ClientList getClients() {
        return clients;
    }

    public FlightList getFlights() {
        return flights;
    }

    public TourList getTours() {
        return tours;
    }

    public ClientPackageList getClientPackages() {
        return clientPackages;
    }

    public Storage() throws TourPlannerException {
        try {
            File fileDirectory = new File(dirPath.toString());
            if (!fileDirectory.exists()) {
                boolean isDirectoryCreated = fileDirectory.mkdir();
                if (isDirectoryCreated) {
                    System.out.println("New folder created!");
                }
            }

            File dataFile = new File(filePath.toString());
            boolean isFileCreated = dataFile.createNewFile();
            if (isFileCreated) {
                System.out.println("New file created!");
            }
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

    public void saveFile(ArrayList<ClientPackage> clientPackages) {
        try {
            FileWriter writer = new FileWriter(filePath.toString());
            for (ClientPackage clientPackage : clientPackages) {
                writer.write(clientPackage.storageString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
