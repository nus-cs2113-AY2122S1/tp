package seedu.duke;

public class AddCommand extends Command {
    private final Client client;

    public AddCommand(Client client) {
        this.client = client;
    }

    public Client getClient() {
        return client;
    }

    @Override
    public void execute(ClientList clients, Ui ui) {
        clients.add(client, ui);
    }
}
