package happybit.goal;

import happybit.exception.HaBitCommandException;
import happybit.habit.Habit;
import happybit.ui.Ui;

import java.util.ArrayList;

public class GoalList {
    private static final String ERROR_EMPTY_GOAL_LIST = "You have no set any goals for yourself yet, would"
            + "you like to set some for yourself?";
    protected ArrayList<Goal> goalList;

    public GoalList() {
        this.goalList = new ArrayList<>();
    }

    public int getListLength() {
        return goalList.size();
    }

    /**
     * Adds a goal to the goalList.
     *
     * @param goal Goal to be added.
     */
    public void addGoal(Goal goal) {
        goalList.add(goal);
    }

    /**
     * Adds a habit that is linked to a goal.
     *
     * @param habit     Habit to be linked to a goal.
     * @param goalIndex Integer index of goal in goalList.
     */
    public void addHabitFromGoal(Habit habit, int goalIndex) {
        goalList.get(goalIndex).addHabit(habit);
    }

    /**
     * Deletes a goal from the goalList.
     *
     * @param goalIndex Integer index of the goal to be deleted from goalList.
     * @throws HaBitCommandException If the goalIndex is not within the index range of the goalList.
     */
    public void deleteGoal(int goalIndex) throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        goalList.remove(goal);
    }

    /**
     * Deletes a habit that is linked to a goal.
     *
     * @param goalIndex  Integer index of goal in goalList.
     * @param habitIndex Integer index of habit to be deleted in goal.
     * @throws HaBitCommandException If the goalIndex is not within the index range of the goalList.
     *                               If the habitIndex is not within the index range of the habitList.
     */
    public void deleteHabitFromGoal(int goalIndex, int habitIndex) throws HaBitCommandException {
        try {
            getGoal(goalIndex).removeHabit(habitIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new HaBitCommandException("Habit index out of range");
        }
    }

    /**
     * List all the goals in the goalList.
     *
     * @param ui User Interface class for printing goalList to output.
     * @throws HaBitCommandException If there are no items in the goalList.
     */
    public void listGoals(Ui ui) throws HaBitCommandException {
        if (goalList.isEmpty()) {
            throw new HaBitCommandException(ERROR_EMPTY_GOAL_LIST);
        }
        ui.printGoalList(goalList);
    }

    /**
     * List all the habits of a goal.
     *
     * @param goalIndex Integer index of goal in goalList.
     * @param ui        User Interface class for printing habitList to output.
     * @throws HaBitCommandException If the goalIndex is not within the range of the goalList.
     *                            If there are no items in the habitList.
     */
    public void listHabitsFromGoal(int goalIndex, Ui ui) throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        ArrayList<Habit> habitList = goal.getHabitList();
        if (habitList.isEmpty()) {
            throw new HaBitCommandException("Habit list is empty");
        }
        ui.printHabitList(habitList);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Get a goal from the goalList.
     *
     * @param goalIndex Integer index of goal in goalList.
     * @return Goal corresponding to the index provided.
     * @throws HaBitCommandException If the goalIndex is not within the range of the goalList.
     */
    private Goal getGoal(int goalIndex) throws HaBitCommandException {
        Goal goal;
        try {
            goal = goalList.get(goalIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new HaBitCommandException("Goal index out of range");
        }
        return goal;
    }

}
