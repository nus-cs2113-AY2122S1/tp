package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class DoneHabitCommand extends Command {
    protected int goalIndex;
    protected int habitIndex;

    public DoneHabitCommand(int goalIndex, int habitIndex) {
        this.goalIndex = goalIndex;
        this.habitIndex = habitIndex;
    }

    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.doneHabitFromGoal(goalIndex, habitIndex, printManager);

        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            printManager.showError(e.getMessage());
        }
    }

    public int getGoalIndex() {
        return goalIndex;
    }

    public int getHabitIndex() {
        return habitIndex;
    }

}

