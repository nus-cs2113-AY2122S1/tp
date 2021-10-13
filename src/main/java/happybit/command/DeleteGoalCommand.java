package happybit.command;

import happybit.exception.HBCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.Ui;

public class DeleteGoalCommand extends DeleteCommand {

    protected int goalIndex;

    public DeleteGoalCommand(int goalIndex) {
        this.goalIndex = goalIndex;
    }

    @Override
    public void runCommand(GoalList goalList, Ui ui, Storage storage) throws HBCommandException {
        goalList.deleteGoal(goalIndex);
    }

}
