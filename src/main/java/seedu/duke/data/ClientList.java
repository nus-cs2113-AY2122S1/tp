package seedu.duke.data;

import seedu.duke.TourPlannerException;

import java.util.ArrayList;
import java.util.Collections;

/**
 * List of clients.
 */
public class ClientList {
    private static final String CLIENT_NOT_FOUND_MESSAGE = "Client cannot be found. Please try another client ID";

    private final ArrayList<Client> clients;
    private ArrayList<String> clientIds;
    private ArrayList<String> clientNames;
    private ArrayList<String> iteratedClientIds;
    private int clientCount = 0;

    /**
     * Class constructor for ClientList.
     */
    public ClientList() {
        clients = new ArrayList<>();
        clientIds = new ArrayList<>();
        clientNames = new ArrayList<>();
        clientCount = 0;
    }

    /**
     * Getter for clients.
     *
     * @return ArrayList containing all clients in the database.
     */
    public ArrayList<Client> getClients() {
        return clients;
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
    public Client getClientByIndex(int index) {
        return clients.get(index);
    }

    /**
     * Getter for client object in the client list, corresponding to the client id given.
     *
     * @param clientId client id of the specific client in the client list
     * @return the client object corresponding to the client id
     * @throws TourPlannerException if client object with clientId cannot be found in clients
     */
    public Client getClientById(String clientId) throws TourPlannerException {
        for (Client client : clients) {
            if (client.getId().equals(clientId)) {
                return client;
            }
        }
        throw new TourPlannerException(CLIENT_NOT_FOUND_MESSAGE);
    }

    /**
     * Getter for client object in the client list, corresponding to the client name given.
     *
     * @param clientName the name of a client in the clientList
     * @return the client object corresponding to the name
     */
    public Client getClientByName(String clientName) throws TourPlannerException {
        for (Client currClient : clients) {
            String clientId = currClient.getId();
            String currClientName = currClient.getName();
            if (currClientName.equals(clientName) && !iteratedClientIds.contains(clientId)) {
                iteratedClientIds.add(clientId);
                return currClient;
            }
        }
        throw new TourPlannerException(CLIENT_NOT_FOUND_MESSAGE);
    }

    /**
     * Main method for adding a client.
     *
     * @param client the client to-be-added
     */
    public void add(Client client) {
        clientCount++;
        clients.add(client);
        clientIds.add(client.getId());
        clientNames.add(client.getName());
    }

    /**
     *  Cuts client from clients.
     *
     * @param client Client object to be deleted from clients
     */
    public void cut(Client client) {
        clients.remove(client);
        clientCount--;
    }

    public ArrayList<String> getSortedClientIds() {
        Collections.sort(clientIds, String.CASE_INSENSITIVE_ORDER);
        return clientIds;
    }

    public ArrayList<String> getSortedClientNames() {
        Collections.sort(clientNames, String.CASE_INSENSITIVE_ORDER);
        return clientNames;
    }

    public void initTempArray() {
        iteratedClientIds = new ArrayList<String>();
    }
}
