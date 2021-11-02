package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

import java.util.Date;

public class UpdateGoalEndDateCommand extends UpdateCommand {
    protected int goalIndex;
    protected Date newDate;

    public UpdateGoalEndDateCommand(int goalIndex, Date newDate) {
        this.goalIndex = goalIndex;
        this.newDate = newDate;
    }

    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.updateGoalEndDate(goalIndex, newDate, printManager);

        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            printManager.printError(e.getMessage());
        }
    }
}
