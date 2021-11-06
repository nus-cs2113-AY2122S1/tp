package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public abstract class DeleteCommand extends Command {

    /**
     * Template method that runs delete commands.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     * @throws HaBitCommandException If exception is thrown by DeleteCommand subclasses.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        // Do nothing
    }

}
