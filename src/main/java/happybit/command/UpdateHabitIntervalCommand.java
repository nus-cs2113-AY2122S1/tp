package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class UpdateHabitIntervalCommand extends UpdateCommand {
    protected int goalIndex;
    protected int habitIndex;
    protected int newInterval;

    /**
     * Constructor for UpdateHabitIntervalCommand.
     *
     * @param goalIndex Index of goal in goalList where habit to be updated is located in
     * @param habitIndex Index of habit in habitList of goal
     * @param newInterval newInterval to be changed to
     */
    public UpdateHabitIntervalCommand(int goalIndex, int habitIndex, int newInterval) {
        this.goalIndex = goalIndex;
        this.habitIndex = habitIndex;
        this.newInterval = newInterval;
    }

    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.updateHabitIntervalFromGoal(goalIndex, habitIndex, newInterval, printManager);
    }

    public int getGoalIndex() {
        return goalIndex;
    }

    public int getHabitIndex() {
        return habitIndex;
    }

    public int getNewInterval() {
        return newInterval;
    }
}
