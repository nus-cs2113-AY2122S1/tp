package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.exception.HaBitStorageException;
import happybit.goal.GoalList;
import happybit.goal.GoalType;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

import java.util.ArrayList;
import java.util.Date;

public class UpdateGoalCommand extends UpdateCommand {
    protected int goalIndex;
    protected String newGoalName; // index 0
    protected GoalType newGoalType; // index 1
    protected Date newGoalEndDate; // index 2
    protected ArrayList<Boolean> updateAttributes;
    protected ArrayList<String> excessAttributes;

    public UpdateGoalCommand(int goalIndex, String goalName, GoalType goalType, Date endDate,
                             ArrayList<Boolean> updateAttributes, ArrayList<String> excessAttributes) {
        this.goalIndex = goalIndex;
        this.newGoalName = goalName;
        this.newGoalType = goalType;
        this.newGoalEndDate = endDate;
        this.updateAttributes = updateAttributes;
        this.excessAttributes = excessAttributes;
    }

    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {
        goalList.updateGoalAttributes(goalIndex, newGoalName, newGoalType, newGoalEndDate,
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

    public String getNewGoalName() {
        return newGoalName;
    }

    public GoalType getNewGoalType() {
        return newGoalType;
    }

    public Date getNewGoalEndDate() {
        return newGoalEndDate;
    }

    public ArrayList<String> getExcessAttributes() {
        return excessAttributes;
    }
}
