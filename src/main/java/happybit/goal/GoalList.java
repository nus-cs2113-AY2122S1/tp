package happybit.goal;

import happybit.exception.HaBitCommandException;
import happybit.habit.Habit;
import happybit.ui.Ui;

import java.util.ArrayList;

public class GoalList {
    private static final String ERROR_EMPTY_GOAL_LIST = "You have no set any goals for yourself yet, would"
            + " you like to set some for yourself?";
    private static final String ERROR_INVALID_GOAL_INDEX = "There is no goal at that index.";
    private static final String ERROR_EMPTY_HABIT_LIST = "There are no habits listed under this goal, add some!";
    private static final String ERROR_INVALID_HABIT_INDEX = "There are no habits at this index in your goal.";

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
     * Adds a goal to the goalList.
     *
     * @param goal Goal to be added.
     * @param ui User Interface class for printing added goal.
     */
    public void addGoal(Goal goal, Ui ui) {
        goalList.add(goal);
        ui.printAddedGoal(goal.getDescription());
    }

    /**
     * Adds a habit that is linked to a goal.
     *
     * @param habit     Habit to be linked to a goal.
     * @param goalIndex Integer index of goal in goalList.
     * @param ui User Interface class for printing added habit to goal in goalList.
     */
    public void addHabitFromGoal(Habit habit, int goalIndex, Ui ui) {
        goalList.get(goalIndex).addHabit(habit);
        ui.printAddedHabit(habit.getHabitName(), goalList.get(goalIndex).getDescription());
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
     * @param ui User Interface class for printing deleted goal to output.
     * @throws HaBitCommandException If the goalIndex is not within the index range of the goalList.
     */
    public void deleteGoal(int goalIndex, Ui ui) throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        goalList.remove(goal);
        ui.printRemovedGoal(goal.getDescription());
    }

    /**
     * Deletes a habit that is linked to a goal.
     *
     * @param goalIndex  Integer index of goal in goalList.
     * @param habitIndex Integer index of habit to be deleted in goal.
     * @param ui User Interface class for printing deleted habit from goal to output.
     * @throws HaBitCommandException If the goalIndex is not within the index range of the goalList.
     *                               If the habitIndex is not within the index range of the habitList.
     */
    public void deleteHabitFromGoal(int goalIndex, int habitIndex, Ui ui) throws HaBitCommandException {
        Goal goal;
        Habit habit;
        try {
            goal = getGoal(goalIndex);
            ArrayList<Habit> habits = goal.getHabitList();
            habit = habits.get(habitIndex); // index out of bounds exception thrown here if invalid
            goal.removeHabit(habitIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new HaBitCommandException(ERROR_INVALID_HABIT_INDEX);
        }
        ui.printRemovedHabit(goal.getDescription(), habit.getHabitName());
    }

    /**
     * Marks a habit under a goal as done
     * Expand upon in v2.0 to include date and description
     *
     * @param goalIndex Integer index of goal in goal list
     * @param habitIndex Integer index of habit to be marked as done in goal
     * @param ui User Interface class for printing Habit marked as done to output.
     * @throws HaBitCommandException If goalIndex is not within index range of goalList.
     *                               if habitIndex is not within index range of the habitList
     */
    public void doneHabitFromGoal(int goalIndex, int habitIndex, Ui ui) throws HaBitCommandException {
        Goal goal;
        Habit habit;
        try {
            goal = getGoal(goalIndex);
            ArrayList<Habit> habits = goal.getHabitList();
            habit = habits.get(habitIndex); // index out of bounds exception thrown here if invalid
            goal.doneHabit(habitIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new HaBitCommandException(ERROR_INVALID_HABIT_INDEX);
        }
        ui.printDoneHabit(goal.getDescription(), habit.getHabitName());
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
        ui.printGoalList(goalList, getListLength());
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
        int numOfHabits = goal.numberOfHabits();
        if (habitList.isEmpty()) {
            throw new HaBitCommandException(ERROR_EMPTY_HABIT_LIST);
        }
        ui.printHabitList(goal.getDescription(), habitList, numOfHabits);
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
            throw new HaBitCommandException(ERROR_INVALID_GOAL_INDEX);
        }
        return goal;
    }

}
