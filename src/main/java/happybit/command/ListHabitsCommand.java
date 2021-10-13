package happybit.command;

import happybit.exception.HBCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.Ui;

public class ListHabitsCommand extends ListCommand {

    protected int goalIndex;

    public ListHabitsCommand(int goalIndex) {
        this.goalIndex = goalIndex;
    }

    @Override
    public void runCommand(GoalList goalList, Ui ui, Storage storage) throws HBCommandException {
        goalList.listHabitsFromGoal(goalIndex, ui);
    }

}
