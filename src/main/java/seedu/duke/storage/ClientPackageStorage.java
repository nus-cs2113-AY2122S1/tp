

package seedu.duke.storage;

import seedu.duke.TourPlannerException;
import seedu.duke.Ui;
import seedu.duke.commands.clientpackages.AddClientPackageCommand;
import seedu.duke.data.ClientList;
import seedu.duke.data.ClientPackage;
import seedu.duke.data.ClientPackageList;
import seedu.duke.data.FlightList;
import seedu.duke.data.TourList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

//@@author Demonshaha

/**
 * Storage class for all ClientPackages. Creates and loads up the ClientPackageList.
 */
public class ClientPackageStorage {
    public static final String EMPTY_STRING = "";
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

    /**
     * Class constructor for ClientPackageStorage.
     * Creates directory and files for storage if they do not already exist.
     *
     * @throws TourPlannerException if IOException is thrown when creating a new directory/file
     * @see File
     * @see IOException
     */
    public ClientPackageStorage() throws TourPlannerException {
        try {
            File fileDirectory = new File(dirPath.toString());
            if (!fileDirectory.exists()) {
                fileDirectory.mkdir();
            }

            File dataFile = new File(filePath.toString());
            dataFile.createNewFile();
        } catch (IOException e) {
            throw new TourPlannerException(EMPTY_STRING);
        }
    }

    /**
     * Getter for ClientPackageList created and populated with client packages retrieved from data file
     * containing previously stored client packages.
     *
     * @return clientPackages retrieved
     */
    public ClientPackageList getClientPackages() {
        return clientPackages;
    }

    /**
     * Reads each line of the file and creates the ClientPackage and adds it to the ClientPackageList
     * using AddClientPackageCommand.
     *
     * @param clients list of clients retrieved from storage file
     * @param tours   list of tours retrieved from storage file
     * @param flights list of flights retrieved from storage file
     * @param ui      user interface used to display messages to the user
     * @throws TourPlannerException if FileNotFoundException or NumberFormatException thrown
     *                              due to corrupted or missing file
     * @see AddClientPackageCommand
     * @see FileNotFoundException
     * @see NumberFormatException
     */
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
                } else if (line.equals("Client: ")) {
                    line = scanner.nextLine();
                    clientId = line.substring(11);
                    rawClientPackage.add(clientId);
                    hasClient = true;
                } else if (line.equals("Tour: ")) {
                    line = scanner.nextLine();
                    tourId = line.substring(9);
                    rawClientPackage.add(tourId);
                    hasTour = true;
                } else if (line.equals("Flight: ")) {
                    line = scanner.nextLine();
                    flightId = line.substring(11);
                    rawClientPackage.add(flightId);
                    hasFlight = true;
                }

                if (hasClient && hasFlight && hasTour && hasClientPackage && !isPackageAdded) {
                    AddClientPackageCommand command =
                            new AddClientPackageCommand(rawClientPackage.toArray(new String[]{}));
                    command.setData(clients, flights, tours, clientPackages, ui);
                    command.executeStorage();
                    isPackageAdded = true;
                }
            }
        } catch (FileNotFoundException | NumberFormatException e) {
            throw new TourPlannerException(EMPTY_STRING);
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

    /**
     * Loops through clientPackages and writes into storage file for ClientPackage.
     */
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
