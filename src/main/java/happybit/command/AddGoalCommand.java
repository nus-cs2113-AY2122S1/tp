package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.Goal;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class AddGoalCommand extends AddCommand {

    protected Goal goal;

    /**
     * Constructor of AddGoalCommand.
     *
     * @param goal Goal to be added.
     */
    public AddGoalCommand(Goal goal) {
        this.goal = goal;
    }

    /**
     * Executes add goal command and adds the goal to the goalList.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.addGoal(this.goal, printManager);

        try {
            int index = goalList.getListLength() - 1;
            storage.export(this.goal, index);
        } catch (HaBitStorageException e) {
            printManager.showError(e.getMessage());
        }
    }

    /**
     * Getter for goal (Used in JUnit test).
     *
     * @return New goal to be added to goalList.
     */
    public Goal getGoal() {
        return goal;
    }
}
