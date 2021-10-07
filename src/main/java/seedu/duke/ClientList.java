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

    public void cut(int taskNumber, Ui ui) {
        Client client = clients.get(taskNumber);
        ui.showCut(client);
        clients.remove(taskNumber);
        clientCount--;
    }

}
