package seedu.duke.data;

import seedu.duke.TourPlannerException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class ClientStorage {
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


    public ArrayList<Client> loadFile() throws FileNotFoundException {
        try {
            ArrayList<Client> clients = new ArrayList<>();

            File dataFile = new File(filePath.toString());
            Scanner scanner = new Scanner(dataFile);
            String clientId = null;
            String clientName = null;
            String clientContactNum = null;
            String clientEmail = null;

            while (scanner.hasNext()) {
                String line = scanner.nextLine();

                if (line.contains("Client List")) {
                    line = scanner.nextLine();
                    clientId = line.substring(11);
                    line = scanner.nextLine();
                    clientName = line.substring(6);
                    line = scanner.nextLine();
                    clientContactNum = line.substring(16);
                    line = scanner.nextLine();
                    clientEmail = line.substring(7);
                }
                String[] clientArray = {clientId, clientName, clientContactNum, clientEmail};
                Client client = new Client(clientArray);
                clients.add(client);
            }
            return clients;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void saveFile(ArrayList<Client> clients) {
        try {
            FileWriter writer = new FileWriter(filePath.toString());
            writer.write("Client List: " + System.lineSeparator());
            for (Client client : clients) {
                writer.write(client.toString() + System.lineSeparator());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
