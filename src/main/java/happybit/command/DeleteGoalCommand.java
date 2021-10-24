package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class DeleteGoalCommand extends DeleteCommand {

    protected int goalIndex;

    public DeleteGoalCommand(int goalIndex) {
        this.goalIndex = goalIndex;
    }

    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.deleteGoal(goalIndex, printManager);

        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            printManager.showError(e.getMessage());
        }
    }

}
