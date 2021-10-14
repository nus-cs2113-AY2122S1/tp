package seedu.duke;

public class CutCommand extends Command {
    private final int clientIndex;

    public CutCommand(int clientIndex) {
        this.clientIndex = clientIndex;
    }

    @Override
    public void execute(ClientList clients, Ui ui) {
        try {
            int newClientCount = clients.getClientCount() - 1;
            clients.cut(clientIndex, ui);
            assert newClientCount == clients.getClientCount();
            assert newClientCount >= 0;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("INVALID: Index out of bounds");
        }
    }
}

