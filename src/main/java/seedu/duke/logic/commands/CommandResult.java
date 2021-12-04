package seedu.duke.logic.commands;

/**
 * Represents the result of the execution of the commands to be displayed to the user.
 */
public class CommandResult {
    public final String messageToBeShown;
    private boolean isBye = false;

    public CommandResult(String messageToBeShown) {
        this.messageToBeShown = messageToBeShown;
    }

    @Override
    public String toString() {
        return this.messageToBeShown;
    }

    public boolean isBye() {
        return isBye;
    }

    public void setBye(boolean bye) {
        isBye = bye;
    }
}
