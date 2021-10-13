package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.Ui;

public class DeleteGoalCommand extends DeleteCommand {

    protected int goalIndex;

    public DeleteGoalCommand(int goalIndex) {
        this.goalIndex = goalIndex;
    }

    @Override
    public void runCommand(GoalList goalList, Ui ui, Storage storage) throws HaBitCommandException {
        goalList.deleteGoal(goalIndex);
    }

}
