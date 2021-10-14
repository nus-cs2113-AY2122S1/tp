package seedu.duke;

/**
 * Deletes a client from the client list.
 */
public class CutCommand extends Command {
    private final int clientIndex;

    /**
     * Class constructor for CutCommand.
     *
     * @param clientIndex index of to-be-deleted client in the client list
     */
    public CutCommand(int clientIndex) {
        this.clientIndex = clientIndex;
    }

    /**
     * Executes the deletion of a specific client from client list, corresponding to his/her index in the list.
     *
     * @param clients existing list of clients
     * @param ui      user interface of TourPlanner
     */
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

