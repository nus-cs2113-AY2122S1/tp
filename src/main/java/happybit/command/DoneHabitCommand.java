package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.Ui;

public class DoneHabitCommand extends Command {
    protected int goalIndex;
    protected int habitIndex;

    public DoneHabitCommand(int goalIndex, int habitIndex) {
        this.goalIndex = goalIndex;
        this.habitIndex = habitIndex;
    }

    @Override
    public void runCommand(GoalList goalList, Ui ui, Storage storage) throws HaBitCommandException {
        goalList.doneHabitFromGoal(goalIndex, habitIndex);
    }

    public int getGoalIndex() {
        return goalIndex;
    }

    public int getHabitIndex() {
        return habitIndex;
    }

}

