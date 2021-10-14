package seedu.duke;

import java.util.ArrayList;

/**
 * List of clients.
 */
public class ClientList {
    private static ArrayList<Client> clients;
    private static int clientCount = 0;

    /**
     * Class constructor for ClientList.
     */
    public ClientList() {
        clients = new ArrayList<>();
        clientCount = 0;
    }

    /**
     * Main method for adding a client.
     *
     * @param client the client to-be-added
     * @param ui     user interface of TourPlanner
     */
    public void add(Client client, Ui ui) {
        clients.add(client);
        ui.showAdd(client);
        clientCount++;
    }

    /**
     * Getter for number of clients in the client list.
     *
     * @return the number of clients in client list.
     */
    public int getClientCount() {
        return clientCount;
    }

    /**
     * Getter for client object in the client list, corresponding to the index given.
     *
     * @param index the index of the specific client in the client list
     * @return the client object corresponding to the index
     */
    public Client getClient(int index) {
        return clients.get(index);
    }
    
    /**
     * Main method for clearing the client list.
     */
    public void clearAllClients() throws TourPlannerException {
        if (clientCount == 0) {
            throw new TourPlannerException("Your client list is currently empty.\n"
                    + "Please first add clients to clear.");
        }
        clients.clear();
        clientCount = 0;
    }

    /**
     * Main method for deleting a client.
     *
     * @param clientIndex the index of the specific client to be deleted from the list.
     * @param ui          user interface of TourPlanner
     */
    public void cut(int clientIndex, Ui ui) {
        Client client = clients.get(clientIndex);
        ui.showCut(client);
        clients.remove(clientIndex);
        clientCount--;
    }
}
