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

public class ClientStorage {
    private final ClientList clients = new ClientList();
    private static final String root = System.getProperty("user.dir");
    private static final Path filePath = Paths.get(root, "data", "TourPlannerClients.txt");
    private static final Path dirPath = Paths.get(root, "data");

    public ClientStorage() throws TourPlannerException {
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

    public ClientList getClients() {
        return clients;
    }

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
            throw new TourPlannerException("File not found!");
        }
    }

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
