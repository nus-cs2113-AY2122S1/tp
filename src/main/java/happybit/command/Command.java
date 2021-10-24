package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public abstract class Command {

    /**
     * Abstract method that runs user input commands.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     * @throws HaBitCommandException If exception is thrown by Command subclasses.
     */
    public abstract void runCommand(GoalList goalList, PrintManager printManager, Storage storage)
            throws HaBitCommandException;

    /**
     * Checks if the exit command is being called.
     *
     * @return True if Command subclass is exitCommand.
     *         False if other Command subclasses.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Checks if the return command is being called.
     *
     * @return True if Command subclass is returnCommand.
     *         False if other Command subclasses.
     */
    public boolean isReturn() {
        return false;
    }

}
