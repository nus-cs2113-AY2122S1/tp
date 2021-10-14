package seedu.duke;

/**
 * Acknowledge that the user is exiting the application.
 */
public class ByeCommand extends Command {

    /**
     * Executes the exit of the loop and returns a goodbye message for acknowledgement.
     *
     * @param clientList existing list of clients
     * @param ui         user interface of TourPlanner
     */
    @Override
    public void execute(ClientList clientList, Ui ui) {
        ui.showBye();
    }

    /**
     * Sets the exit condition to true since ByeCommand is initialised.
     *
     * @return true
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
