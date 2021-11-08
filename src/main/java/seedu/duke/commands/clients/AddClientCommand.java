package seedu.duke.commands.clients;

import seedu.duke.commands.Command;
import seedu.duke.data.Client;

/**
 * Adds a client into the database.
 */
public class AddClientCommand extends Command {
    private final Client client;


    /**
     * Class constructor for AddClientCommand.
     *
     * @param client client to be added
     */
    public AddClientCommand(Client client) {
        this.client = client;
    }

    /**
     * Returns the client object that was added.
     *
     * @return the added client object
     */
    public Client getClient() {
        return client;
    }

    /**
     * Executes the addition of client to client list.
     * If given client ID already exists, the client will not be added.
     */
    @Override
    public void execute() {

        int count = clients.getClientCount();
        for (int i = 0; i < count; i++) {
            Client currClient = clients.getClientByIndex(i);
            boolean sameId = currClient.getId().equals(client.getId());
            if (sameId) {
                System.out.println("ERROR: Client ID already exists. Please try another client code.");
                return;
            }
            boolean sameName = currClient.getName().equals(client.getName());
            boolean sameContactNum = currClient.getContactNum().equals(client.getContactNum());
            boolean sameEmail = currClient.getEmail().equals(client.getEmail());
            if (sameName && sameContactNum && sameEmail) {
                System.out.println("ERROR: Client with same fields already exists with different ID.");
                return;
            }
        }
        clients.add(client);
        ui.showAdd(client);
    }
}
