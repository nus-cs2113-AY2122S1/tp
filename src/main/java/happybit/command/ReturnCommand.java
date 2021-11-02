package happybit.command;

import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class ReturnCommand extends Command {

    /**
     * Template method that runs return command.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) {
        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            printManager.printError(e.getMessage());
        }
    }

    /**
     * Triggers the return flag to return to startup view.
     *
     * @return True.
     */
    @Override
    public boolean isReturn() {
        return true;
    }

}
