package seedu.duke.commands.clients;

import seedu.duke.TourPlannerException;
import seedu.duke.data.Client;
import seedu.duke.commands.Command;

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
        try {
            Client existingClient = clients.getClientById(client.getId());
            System.out.println("ERROR: Client ID already exists. Please try another client ID.");
        } catch (TourPlannerException e) {
            clients.add(client);
            ui.showAdd(client);
        }
    }
}
