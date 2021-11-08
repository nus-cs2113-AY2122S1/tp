package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

import java.util.ArrayList;


public class UpdateHabitCommand extends UpdateCommand {
    protected int goalIndex;
    protected int habitIndex;
    protected String newHabitName; // index 0
    protected int newHabitInterval; // index 1
    protected ArrayList<Boolean> updateAttributes;
    protected ArrayList<String> excessAttributes;

    public UpdateHabitCommand(int goalIndex, int habitIndex, String habitName, int interval,
                              ArrayList<Boolean> updateAttributes, ArrayList<String> excessAttributes) {
        this.goalIndex = goalIndex;
        this.habitIndex = habitIndex;
        this.newHabitName = habitName;
        this.newHabitInterval = interval;
        this.updateAttributes = updateAttributes;
        this.excessAttributes = excessAttributes;
    }

    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.updateHabitAttributes(goalIndex, habitIndex, newHabitName, newHabitInterval,
                updateAttributes, excessAttributes, printManager);
        try {
            storage.export(goalList.getGoalList());
        } catch (HaBitStorageException e) {
            printManager.printError(e.getMessage());
        }
    }

    public int getGoalIndex() {
        return goalIndex;
    }

    public int getHabitIndex() {
        return habitIndex;
    }

    public String getNewHabitName() {
        return newHabitName;
    }

    public int getNewHabitInterval() {
        return newHabitInterval;
    }

    public ArrayList<String> getExcessAttributes() {
        return excessAttributes;
    }
}
