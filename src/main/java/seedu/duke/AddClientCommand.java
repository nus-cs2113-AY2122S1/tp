package seedu.duke;

/**
 * Adds a client into the database.
 */
public class AddClientCommand extends Command {
    private final Client client;


    /**
     * Class constructor for AddCommand.
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
     */
    @Override
    public void execute() {
        int newClientCount = clients.getClientCount() + 1;
        clients.add(client, ui);
        assert newClientCount == clients.getClientCount();
    }
}
