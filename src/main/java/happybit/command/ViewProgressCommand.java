package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class ViewProgressCommand extends Command {

    protected int goalIndex;

    /**
     * Constructor of ViewProgressCommand.
     *
     * @param goalIndex Index of goal in goalList, where the progress is to be printed.
     */
    public ViewProgressCommand(int goalIndex) {
        this.goalIndex = goalIndex;
    }

    /**
     * Executes view progress command and shows the user progress of a goal.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     * @throws HaBitCommandException If the goalIndex provided is invalid.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        // todo
    }
}
