package seedu.duke;

public class ClearCommand extends Command {
    /**
     * Executes the clearing of all clients in the client list.
     *
     * @param clientList existing list of clients
     * @param ui         user interface of TourPlanner
     */
    public void execute(ClientList clientList, Ui ui) {
        clientList.clearAllClients();
        ui.showClear();
    }
}
