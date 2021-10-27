package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class ViewStreakCommand extends Command {
    protected int goalIndex;
    protected int habitIndex;

    /**
     * Constructor of ViewStreakCommand.
     *
     * @param goalIndex Index of goal in goalList
     * @param habitIndex Index of habit in habitList of specified goal, where
     */
    public ViewStreakCommand(int goalIndex, int habitIndex) {
        this.goalIndex = goalIndex;
        this.habitIndex = habitIndex;
    }

    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {

        goalList.viewHabitStreak(goalIndex, habitIndex, printManager);
        // todo add Huien export
    }
}
