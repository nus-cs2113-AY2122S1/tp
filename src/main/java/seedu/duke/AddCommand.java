package seedu.duke;

public class AddCommand extends Command {
    private final Client client;

    /**
     * Class constructor for AddCommand.
     *
     * @param client client to be added
     */
    public AddCommand(Client client) {
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
     *
     * @param clients existing list of clients
     * @param ui      user interface of TourPlanner
     */
    @Override
    public void execute(ClientList clients, Ui ui) {
        clients.add(client, ui);
    }
}
