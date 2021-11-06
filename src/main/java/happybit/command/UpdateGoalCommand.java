package happybit.command;

import happybit.exception.HaBitCommandException;
import happybit.goal.GoalList;
import happybit.goal.GoalType;
import happybit.storage.Storage;
import happybit.ui.PrintManager;

import java.util.ArrayList;
import java.util.Date;

public class UpdateGoalCommand extends UpdateCommand{
    protected int goalIndex;
    protected String newGoalName; // index 0
    protected GoalType newGoalType; // index 1
    protected Date newGoalEndDate; // index 2
    protected int[] updateAttributes;
    protected ArrayList<String> excessAttributes;

    public UpdateGoalCommand(int goalIndex, String goalName, GoalType goalType, Date endDate,
            int[] updateAttributes, ArrayList<String> excessAttributes) {
        this.goalIndex = goalIndex;
        this.newGoalName = goalName;
        this.newGoalType = goalType;
        this.newGoalEndDate = endDate;
        this.updateAttributes = updateAttributes;
        this.excessAttributes = excessAttributes;
    }

    @Override
    public void runCommand(GoalList goalList, PrintManager printManager, Storage storage) throws HaBitCommandException {

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

    public int[] getUpdateAttributes() {
        return updateAttributes;
    }

    public ArrayList<String> getExcessAttributes() {
        return excessAttributes;
    }
}
