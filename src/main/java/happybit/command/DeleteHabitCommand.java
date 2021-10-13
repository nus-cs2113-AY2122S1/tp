package happybit.command;

import happybit.exception.HBCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.Ui;

public class DeleteHabitCommand extends DeleteCommand {

    protected int goalIndex;
    protected int habitIndex;

    public DeleteHabitCommand(int goalIndex, int habitIndex) {
        this.goalIndex = goalIndex;
        this.habitIndex = habitIndex;
    }

    @Override
    public void runCommand(GoalList goalList, Ui ui, Storage storage) throws HBCommandException {
        goalList.deleteHabitFromGoal(goalIndex, habitIndex);
    }

}
