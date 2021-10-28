package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class SetCommand extends Command {

    protected int goalIndex;

    /**
     * Constructor for SetCommand.
     *
     * @param goalIndex Index of goal in goalList, where the user would like to set.
     */
    public SetCommand(int goalIndex) {
        this.goalIndex = goalIndex;
    }

    /**
     * Executes set command and sets the goal.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     * @throws HaBitCommandException If the goalIndex provided is invalid.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.setChosenGoalIndex(goalIndex);
    }

}
