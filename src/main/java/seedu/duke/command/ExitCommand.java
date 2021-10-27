package seedu.duke.command;

import seedu.duke.model.Shelf;

public class ExitCommand extends Command {

    private static final String BYE_MESSAGE = "See you next time";

    /**
     * Override superclass Command to not perform any actions.
     */
    public String execute() {
        // System.out.println(BYE_MESSAGE);
        return BYE_MESSAGE;
    }

    /**
     * Checks if exit command is called.
     *
     * @return true if exit command called
     */
    public boolean isExit() {
        return true;
    }
}
