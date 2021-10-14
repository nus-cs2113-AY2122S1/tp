package seedu.duke;

/**
 * Abstract command that outlines the 'execute' and 'isExit' method.
 */
public abstract class Command {

    /**
     * Executes the specific command depending on the command constructed.
     *
     * @param clients the existing list of clients
     * @param ui      user interface of TourPlanner
     */
    public abstract void execute(ClientList clients, Ui ui);

    /**
     * Function that controls the exit condition of the loop.
     *
     * @return the exit condition from the loop
     */
    public boolean isExit() {
        return false;
    }
}
