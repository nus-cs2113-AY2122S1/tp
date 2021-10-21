package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class UpdateGoalNameCommand extends UpdateCommand {

    protected int goalIndex;
    protected String goalName;

    public UpdateGoalNameCommand(int goalIndex, String goalName) {
        this.goalIndex = goalIndex;
        this.goalName = goalName;
    }

    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.updateGoalName(goalIndex, goalName, printManager);
    }
}
