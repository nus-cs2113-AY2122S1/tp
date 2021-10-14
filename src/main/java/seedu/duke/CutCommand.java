package seedu.duke;

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
            ui.showCut(clients.getClient(clientIndex));
            clients.cut(clientIndex, ui);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("INVALID: Index out of bounds");
        }
    }
}

