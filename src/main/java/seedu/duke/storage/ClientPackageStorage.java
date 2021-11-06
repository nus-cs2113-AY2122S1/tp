package seedu.duke.storage;

import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.commands.clientpackages.AddClientPackageCommand;
import seedu.duke.data.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientPackageStorage {
    private final ClientPackageList clientPackages = new ClientPackageList();
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "TourPlannerClientPackages.txt");
    private static final Path dirPath = Paths.get(root, "data");
    private static boolean hasClient = false;
    private static boolean hasTour = false;
    private static boolean hasFlight = false;
    private static boolean hasClientPackage = false;
    private static boolean isPackageAdded = false;
    private ArrayList<String> rawClientPackage = new ArrayList<>();

    public ClientPackageList getClientPackages() {
        return clientPackages;
    }

    public ClientPackageStorage() throws TourPlannerException {
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

    public void loadFile(ClientList clients, TourList tours, FlightList flights, Ui ui) throws TourPlannerException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);

            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String clientPackageId;
                String clientId;
                String tourId;
                String flightId;

                if (line.equals("Client Package Details: ")) {
                    resetCheckerStates();
                } else if (line.contains("Package ID")) {
                    clientPackageId = line.substring(12);
                    rawClientPackage.add(clientPackageId);
                    hasClientPackage = true;
                    System.out.println("a");
                } else if (line.equals("Client: ")) {
                    line = scanner.nextLine();
                    clientId = line.substring(11);
                    rawClientPackage.add(clientId);
                    System.out.println("b");
                    hasClient = true;
                } else if (line.equals("Tour: ")) {
                    line = scanner.nextLine();
                    tourId = line.substring(9);
                    rawClientPackage.add(tourId);
                    System.out.println("c");
                    hasTour = true;
                } else if (line.equals("Flight: ")) {
                    line = scanner.nextLine();
                    flightId = line.substring(11);
                    rawClientPackage.add(flightId);
                    System.out.println("d");
                    hasFlight = true;
                }

                System.out.println(hasClient);
                System.out.println(hasFlight);
                System.out.println(hasTour);
                System.out.println(hasClientPackage);
                System.out.println(isPackageAdded);
                if (hasClient && hasFlight && hasTour && hasClientPackage && !isPackageAdded) {
                    System.out.println("HELLO");
                    AddClientPackageCommand command = new AddClientPackageCommand(rawClientPackage.toArray(new String[]{}));
                    command.setData(clients, flights, tours, clientPackages, ui);
                    command.executeStorage();
                    System.out.println("e");
                    isPackageAdded = true;
                }
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            throw new TourPlannerException("File not found!");
        }
    }

    private void resetCheckerStates() {
        hasClient = false;
        hasFlight = false;
        hasTour = false;
        hasClientPackage = false;
        isPackageAdded = false;
        rawClientPackage = new ArrayList<>();
    }

    public void saveFile() {
        try {
            ArrayList<ClientPackage> clientPackageList = clientPackages.getClientPackages();
            FileWriter writer = new FileWriter(filePath.toString());
            for (ClientPackage clientPackage : clientPackageList) {
                writer.write("Client Package Details: \n");
                writer.write(clientPackage.toString() + "\n\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
