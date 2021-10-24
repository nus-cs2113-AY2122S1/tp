package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.Goal;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class AddGoalCommand extends AddCommand {

    protected Goal goal;

    public AddGoalCommand(Goal goal) {
        this.goal = goal;
    }

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

}
