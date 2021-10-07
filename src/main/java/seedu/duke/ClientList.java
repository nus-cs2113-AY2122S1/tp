package seedu.duke;

import java.util.ArrayList;

public class ClientList {
    private static ArrayList<Client> clients;
    private static int clientCount = 0;

    public ClientList() {
        clients = new ArrayList<>();
        clientCount = 0;
    }

    public void add(Client client) {
        clients.add(client);
        clientCount++;
    }

    public int getClientCount() {
        return clientCount;
    }

    public Client getClient(int index) {
        return clients.get(index);
    }

    public void clearAllClients() {
        clients.clear();
    }

    public void cut(int clientIndex, Ui ui) {
        Client client = clients.get(clientIndex);
        ui.showCut(client);
        clients.remove(clientIndex);
        clientCount--;
    }

}
