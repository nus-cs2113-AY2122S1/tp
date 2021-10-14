package seedu.duke;

/**
 * Clears the entire client list.
 */
public class ClearCommand extends Command {
  
    /**
     * Executes the clearing of all clients in the client list.
     *
     * @param clientList existing list of clients
     * @param ui         user interface of TourPlanner
     */
    public void execute(ClientList clientList, Ui ui) {
        try {
            clientList.clearAllClients();
            assert clientList.getClientCount() == 0;
            ui.showClear();
        } catch (TourPlannerException e) {
            ui.show(e.getMessage());
        }
    }
}
