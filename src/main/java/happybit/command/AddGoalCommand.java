package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.Goal;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.Ui;

public class AddGoalCommand extends AddCommand {

    protected Goal goal;

    public AddGoalCommand(Goal goal) {
        this.goal = goal;
    }

    @Override
    public void runCommand(GoalList goalList, Ui ui, Storage storage) throws HaBitCommandException {
        goalList.addGoal(goal, ui);
    }

}
