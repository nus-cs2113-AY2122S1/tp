package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

public class ListHabitsCommand extends ListCommand {

    protected int goalIndex;

    public ListHabitsCommand(int goalIndex) {
        this.goalIndex = goalIndex;
    }

    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.listHabitsFromGoal(goalIndex, printManager);
    }

    public int getGoalIndex() {
        return goalIndex;
    }
}
