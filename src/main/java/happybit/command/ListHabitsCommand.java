package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class ListHabitsCommand extends ListCommand {

    protected int goalIndex;

    /**
     * Constructor for ListHabitsCommand.
     *
     * @param goalIndex Index of goal in goalList, where a list of habits is to be printed.
     */
    public ListHabitsCommand(int goalIndex) {
        this.goalIndex = goalIndex;
    }

    /**
     * Executes list habits command and prints out all habits of the goal.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     * @throws HaBitCommandException If the goalIndex provided is invalid, or the habit list is empty.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.listHabitsFromGoal(goalIndex, printManager);
    }

    /**
     * Getter for goalIndex (Used in JUnit test).
     *
     * @return Index of goal in goalList whose habits are to be printed.
     */
    public int getGoalIndex() {
        return goalIndex;
    }
}
