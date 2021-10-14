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
        int newClientCount = clients.getClientCount() + 1;
        clients.add(client, ui);
        assert newClientCount == clients.getClientCount();
    }
}
