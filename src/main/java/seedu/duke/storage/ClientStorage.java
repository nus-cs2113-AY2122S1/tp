package seedu.duke.storage;

import seedu.duke.TourPlannerException;
import seedu.duke.data.Client;
import seedu.duke.data.ClientList;

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
 * Storage class for all Clients. Creates and loads up the ClientList.
 */
public class ClientStorage {
    public static final String EMPTY_STRING = "";
    private final ClientList clients = new ClientList();
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "TourPlannerClients.txt");
    private static final Path dirPath = Paths.get(root, "data");

    /**
     * Class constructor for ClientStorage.
     * Creates directory and files for storage if they do not already exist.
     *
     * @throws TourPlannerException if IOException is thrown when creating a new directory/file
     * @see File
     * @see IOException
     */
    public ClientStorage() throws TourPlannerException {
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
     * Getter for ClientList created and populated with clients retrieved from data file
     * containing previously stored clients.
     *
     * @return clients retrieved
     */
    public ClientList getClients() {
        return clients;
    }

    /**
     * Reads each line of the file and identifies client fields -- clientId, name, contactNum and mail.
     * Creates new Client object based on the client fields and adds to ClientList.
     *
     * @throws TourPlannerException if FileNotFoundException due to corrupted or missing file
     * @see FileNotFoundException
     */
    public void loadFile() throws TourPlannerException {
        try {
            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);
            String clientId;
            String clientName;
            String clientContactNum;
            String clientEmail;

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.contains("Client Details:")) {
                    line = scanner.nextLine();
                    clientId = line.substring(11);
                    line = scanner.nextLine();
                    clientName = line.substring(6);
                    line = scanner.nextLine();
                    clientContactNum = line.substring(16);
                    line = scanner.nextLine();
                    clientEmail = line.substring(7);
                    String[] clientArray = {clientId, clientName, clientContactNum, clientEmail};
                    Client client = new Client(clientArray);
                    clients.add(client);
                }
            }
        } catch (FileNotFoundException e) {
            throw new TourPlannerException(EMPTY_STRING);
        }
    }

    /**
     * Loops through clients and writes into storage file for Client.
     */
    public void saveFile() {
        try {
            ArrayList<Client> clientList = clients.getClients();
            FileWriter writer = new FileWriter(filePath.toString());
            for (Client client : clientList) {
                writer.write("Client Details: \n");
                writer.write(client.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
