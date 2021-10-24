package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.habit.Habit;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class AddHabitCommand extends AddCommand {

    protected Habit habit;
    protected int goalIndex;

    public AddHabitCommand(Habit habit, int goalIndex) {
        this.habit = habit;
        this.goalIndex = goalIndex;
    }

    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.addHabitToGoal(this.habit, goalIndex, printManager);

        try {
            storage.export(this.habit, goalIndex);
        } catch (HaBitStorageException e) {
            printManager.showError(e.getMessage());
        }
    }

}
