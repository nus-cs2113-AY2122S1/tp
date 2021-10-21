package seedu.duke;

/**
 * Clears the entire client list.
 */
public class ClearCommand extends Command {

    public void execute(ClientList clients, FlightList flights, TourList tours, Ui ui) {
        try {
            clients.clearAllClients();
            assert clients.getClientCount() == 0;
            ui.showClear();
        } catch (TourPlannerException e) {
            ui.show(e.getMessage());
        }
    }
}
