package seedu.duke.commands;

/**
 * Acknowledge that the user is exiting the application.
 */
public class ByeCommand extends Command {


    @Override
    public void execute() {
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
