package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class DeleteGoalCommand extends DeleteCommand {

    protected int goalIndex;

    /**
     * Constructor of DeleteGoalCommand.
     *
     * @param goalIndex Index of goal in goalList to be deleted.
     */
    public DeleteGoalCommand(int goalIndex) {
        this.goalIndex = goalIndex;
    }

    /**
     * Executes delete goal command and removes goal from goalList.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     * @throws HaBitCommandException If the goalIndex provided is invalid.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.deleteGoal(goalIndex, printManager);

        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            printManager.printError(e.getMessage());
        }
    }

    /**
     * Getter for goalIndex (Used in JUnit test).
     *
     * @return Index of goal to be deleted from goalList.
     */
    public int getGoalIndex() {
        return goalIndex;
    }

}
