package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.habit.Habit;
import happybit.storage.Storage;
import happybit.ui.Ui;

public class AddHabitCommand extends AddCommand {

    protected Habit habit;
    protected int goalIndex;

    public AddHabitCommand(Habit habit, int goalIndex) {
        this.habit = habit;
        this.goalIndex = goalIndex;
    }

    @Override
    public void runCommand(GoalList goalList, Ui ui, Storage storage) throws HaBitCommandException {
        goalList.addHabitToGoal(habit, goalIndex, ui);
    }

}
