package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class UpdateGoalNameCommand extends UpdateCommand {

    protected int goalIndex;
    protected String goalName;

    /**
     * Constructor for UpdateGoalNameCommand.
     *
     * @param goalIndex Index of goal in goalList, where the goalName is to be updated.
     * @param goalName  New name of the goal.
     */
    public UpdateGoalNameCommand(int goalIndex, String goalName) {
        this.goalIndex = goalIndex;
        this.goalName = goalName;
    }

    /**
     * Executes update goal name command and changes the goalName.
     *
     * @param goalList     List that stores all the goals.
     * @param printManager Prints messages to the console.
     * @param storage      Reference to the file where data is stored.
     * @throws HaBitCommandException If the goalIndex provided is invalid.
     */
    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.updateGoalName(goalIndex, goalName, printManager);

        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            printManager.showError(e.getMessage());
        }
    }
}
