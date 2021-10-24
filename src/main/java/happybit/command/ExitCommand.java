package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class ExitCommand extends Command {

    /**
     * Template method that runs exit command.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            printManager.showError(e.getMessage());
        }
    }

    /**
     * Triggers the exit flag to end the application.
     *
     * @return True.
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
