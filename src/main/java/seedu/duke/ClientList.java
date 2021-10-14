package seedu.duke;

import java.util.ArrayList;

public class ClientList {
    private static ArrayList<Client> clients;
    private static int clientCount = 0;

    public ClientList() {
        clients = new ArrayList<>();
        clientCount = 0;
    }

    public void add(Client client, Ui ui) {
        clients.add(client);
        ui.showAdd(client);
        clientCount++;
    }

    public int getClientCount() {
        return clientCount;
    }

    public Client getClient(int index) {
        return clients.get(index);
    }

    public void clearAllClients() throws TourPlannerException {
        if (clientCount == 0) {
            throw new TourPlannerException("Your client list is currently empty.\n"
                    + "Please first add clients to clear.");
        }
        clients.clear();
        clientCount = 0;
    }

    public void cut(int clientIndex, Ui ui) {
        Client client = clients.get(clientIndex);
        ui.showCut(client);
        clients.remove(clientIndex);
        clientCount--;
    }
}
