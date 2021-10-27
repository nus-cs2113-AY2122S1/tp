package seedu.duke;

import seedu.duke.data.Client;
import seedu.duke.data.ClientPackage;
import seedu.duke.data.Flight;
import seedu.duke.data.Tour;

import java.lang.reflect.Array;
import java.nio.file.Path;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "TourPlanner.txt");
    private static final Path dirPath = Paths.get(root, "data");

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

    public ArrayList<ClientPackage> loadFile() throws FileNotFoundException {
        try {
            ArrayList<ClientPackage> clientPackages = new ArrayList<>();

            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String clientPackageId = null;
                String clientId = null;
                String clientName = null;
                String clientContactNum = null;
                String clientEmail = null;
                String tourName = null;
                String tourCode = null;
                String tourPrice = null;
                String flightId = null;
                String returnDestination = null;
                String departDestination = null;
                String returnDate = null;
                String departDate = null;
                if (line.contains("Package ID")) {
                    clientPackageId = line.substring(12);
                } else if (line.contains("Client")) {
                    line = scanner.nextLine();
                    clientId = line.substring(11);
                    line = scanner.nextLine();
                    clientName = line.substring(6);
                    line = scanner.nextLine();
                    clientContactNum = line.substring(16);
                    line = scanner.nextLine();
                    clientEmail = line.substring(7);
                } else if (line.contains("Tour")) {
                    line = scanner.nextLine();
                    tourName = line.substring(6);
                    line = scanner.nextLine();
                    tourCode = line.substring(6);
                    line = scanner.nextLine();
                    int index = line.indexOf("$");
                    tourPrice = line.substring(index + 1);
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
                }
                String[] clientDetails = {clientId, clientName, clientContactNum, clientEmail};
                String[] tourDetails = {tourCode, tourName, tourPrice};
                String[] flightDetails = {flightId, departDestination, returnDestination, departDate, returnDate};

                Client client = new Client(clientDetails);
                Tour tour = new Tour(tourDetails);
                Flight flight = new Flight(flightDetails);

                ClientPackage clientPackage = new ClientPackage(clientPackageId, client, tour, flight);
                clientPackages.add(clientPackage);
            }
            return clientPackages;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveFile(ArrayList<ClientPackage> clientPackages) {
        try {
            FileWriter writer = new FileWriter(filePath.toString());
            for (ClientPackage clientPackage : clientPackages) {
                writer.write(clientPackage.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
