package happybit.command;

import happybit.exception.HBCommandException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.Ui;

public class AddHabitCommand extends AddCommand {

    protected String habitName;
    protected int goalIndex;

    public AddHabitCommand(String habitName, int goalIndex) {
        this.habitName = habitName;
        this.goalIndex = goalIndex;
    }

    @Override
    public void runCommand(GoalList goalList, Ui ui, Storage storage) throws HBCommandException {

    }

}
