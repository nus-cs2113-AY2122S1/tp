package happybit.goal;

import happybit.exception.HappyBitException;
import happybit.ui.Ui;

import java.util.ArrayList;

public class GoalList {
    private static final String ERROR_EMPTY_GOAL_LIST = "You have no set any goals for yourself yet, would"
            + "you like to set some for yourself?";
    protected ArrayList<Goal> goalList;

    public GoalList() {
        this.goalList = new ArrayList<>();
    }

    public static int getListLength() {
        return goals.size();
    }

    /**
     * Add Goal to goals; Set Command.
     */
    public void addGoal(Goal goal) {
        goalList.add(goal);
    }

    /**
     * Delete Goal from goals; Remove command.
     */
    public static void removeGoal(int goalIndex) throws IndexOutOfBoundsException {
        Goal currGoal = goals.get(goalIndex);
        goals.remove(currGoal);
        Ui.printRemovedGoal(currGoal.getDescription());
    }

    public static void listGoals() throws HappyBitException {
        if (goals.isEmpty()) {
            throw new HappyBitException(ERROR_EMPTY_GOAL_LIST);
        }
        Ui.printGoalsList(goals);
    }

}
