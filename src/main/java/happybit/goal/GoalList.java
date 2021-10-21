package happybit.goal;

import happybit.exception.HaBitCommandException;
import happybit.habit.Habit;
import happybit.ui.PrintManager;

import java.util.ArrayList;

public class GoalList {
    private static final String ERROR_EMPTY_GOAL_LIST = "You have not set any goals for yourself yet, would"
            + " you like to set some for yourself?";
    private static final String ERROR_INVALID_GOAL_INDEX = "There is no goal at that index.";
    private static final String ERROR_EMPTY_HABIT_LIST = "There are no habits listed under this goal, add some!";
    private static final String ERROR_INVALID_HABIT_INDEX = "There are no habits at this index in your goal.";

    protected ArrayList<Goal> goalList;
    protected int chosenGoalIndex;

    public GoalList() {
        this.goalList = new ArrayList<>();
        this.chosenGoalIndex = -1;
    }

    public ArrayList<Goal> getGoalList() {
        return goalList;
    }

    /**
     * Getter for index of chosen goal.
     * @return Integer index of chosen goal.
     */
    public int getChosenGoalIndex() {
        return chosenGoalIndex;
    }

    /**
     * Gets the goal type of the chosen goal.
     *
     * @return Goal type of the chosen goal.
     */
    public GoalType getChosenGoalType() {
        GoalType goalType = GoalType.DEFAULT;
        try {
            goalType = getGoal(chosenGoalIndex).goalType;
        } catch (HaBitCommandException e) {
            // Exception should never be thrown here
        }
        return goalType;
    }

    /**
     * Sets a goal number to be the index of a goal in the goalList.
     *
     * @param goalIndex Integer index of a goal.
     */
    public void setChosenGoalIndex(int goalIndex) throws HaBitCommandException {
        getGoal(goalIndex);  // Checks if goal corresponding to goal index exists
        this.chosenGoalIndex = goalIndex;

    }

    public int getListLength() {
        return goalList.size();
    }

    /**
     * Adds a goal to the goalList.
     * For storage
     *
     * @param goal Goal to be added.
     */
    public void addGoal(Goal goal) {
        goalList.add(goal);
    }

    /**
     * Adds a goal to the goalList.
     * With Ui for printing message
     *
     * @param goal Goal to be added.
     * @param printManager User Interface class for printing added goal.
     */
    public void addGoal(Goal goal, PrintManager printManager) {
        goalList.add(goal);
        printManager.printAddedGoal(goal.getDescription());
    }

    /**
     * Adds a habit that is linked to a goal.
     * With Ui for printing message.
     *
     * @param habit     Habit to be linked to a goal.
     * @param goalIndex Integer index of goal in goalList.
     * @param printManager User Interface class for printing added habit to goal in goalList.
     */
    public void addHabitToGoal(Habit habit, int goalIndex, PrintManager printManager) throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        goal.addHabit(habit);
        printManager.printAddedHabit(habit.getHabitName(), goal.getDescription());
    }

    /**
     * Adds a habit that is linked to a goal.
     * For storage.
     *
     * @param habit     Habit to be linked to a goal.
     * @param goalIndex Integer index of goal in goalList.
     */
    public void addHabitToGoal(Habit habit, int goalIndex) throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        goal.addHabit(habit);
    }

    /**
     * Deletes a goal from the goalList.
     *
     * @param goalIndex Integer index of the goal to be deleted from goalList.
     * @param printManager User Interface class for printing deleted goal to output.
     * @throws HaBitCommandException If the goalIndex is not within the index range of the goalList.
     */
    public void deleteGoal(int goalIndex, PrintManager printManager) throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        updateChosenGoalIndex(goalIndex);
        goalList.remove(goal);
        printManager.printRemovedGoal(goal.getDescription());
    }

    /**
     * Deletes a habit that is linked to a goal.
     *
     * @param goalIndex  Integer index of goal in goalList.
     * @param habitIndex Integer index of habit to be deleted in goal.
     * @param printManager User Interface class for printing deleted habit from goal to output.
     * @throws HaBitCommandException If the goalIndex is not within the index range of the goalList.
     *                               If the habitIndex is not within the index range of the habitList.
     */
    public void deleteHabitFromGoal(int goalIndex, int habitIndex, PrintManager printManager) throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        ArrayList<Habit> habits = goal.getHabitList();
        Habit habit;
        try {
            habit = habits.get(habitIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new HaBitCommandException(ERROR_INVALID_HABIT_INDEX);
        }
        goal.removeHabit(habitIndex);
        printManager.printRemovedHabit(goal.getDescription(), habit.getHabitName());
    }

    /**
     * Marks a habit under a goal as done
     * Expand upon in v2.0 to include date and description
     *
     * @param goalIndex Integer index of goal in goal list
     * @param habitIndex Integer index of habit to be marked as done in goal
     * @param printManager User Interface class for printing Habit marked as done to output.
     * @throws HaBitCommandException If goalIndex is not within index range of goalList.
     *                               if habitIndex is not within index range of the habitList
     */
    public void doneHabitFromGoal(int goalIndex, int habitIndex, PrintManager printManager) throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        ArrayList<Habit> habits = goal.getHabitList();
        Habit habit;
        try {
            habit = habits.get(habitIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new HaBitCommandException(ERROR_INVALID_HABIT_INDEX);
        }
        goal.doneHabit(habitIndex);
        printManager.printDoneHabit(goal.getDescription(), habit.getHabitName());
    }

    /**
     * Lists all the goals in the goalList.
     *
     * @param printManager User Interface class for printing goalList to output.
     * @throws HaBitCommandException If there are no items in the goalList.
     */
    public void listGoals(PrintManager printManager) throws HaBitCommandException {
        if (goalList.isEmpty()) {
            throw new HaBitCommandException(ERROR_EMPTY_GOAL_LIST);
        }
        printManager.printGoalList(goalList, getListLength());
    }

    /**
     * Lists all the habits of a goal.
     *
     * @param goalIndex Integer index of goal in goalList.
     * @param printManager        User Interface class for printing habitList to output.
     * @throws HaBitCommandException If the goalIndex is not within the range of the goalList.
     *                            If there are no items in the habitList.
     */
    public void listHabitsFromGoal(int goalIndex, PrintManager printManager) throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        ArrayList<Habit> habitList = goal.getHabitList();
        int numOfHabits = goal.numberOfHabits();
        if (habitList.isEmpty()) {
            throw new HaBitCommandException(ERROR_EMPTY_HABIT_LIST);
        }
        printManager.printHabitList(goal.getDescription(), habitList, numOfHabits);
    }

    /**
     * Changes and updates the name of a goal with a new name from user.
     *
     * @param goalIndex Index of the goal in goalList.
     * @param newGoalName New name user wants to change the goal to.
     * @param printManager User Interface class for printing the update made to output.
     * @throws HaBitCommandException If the goalIndex is not within the range of the goalList.
     */
    public void updateGoalName(int goalIndex, String newGoalName, PrintManager printManager) throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        String oldGoalName = goal.getGoalName();
        goal.setGoalName(newGoalName);
        goalList.set(goalIndex, goal);
        printManager.printUpdatedGoal(oldGoalName, newGoalName);
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

    /**
     * Updates the chosen goal index when a goal is deleted.
     *
     * @param deletedGoalIndex Goal index to be deleted.
     */
    private void updateChosenGoalIndex(int deletedGoalIndex) {
        if (this.chosenGoalIndex == deletedGoalIndex) {
            this.chosenGoalIndex = -1;
        } else if (this.chosenGoalIndex > deletedGoalIndex) {
            this.chosenGoalIndex -= 1;
        }
    }

}
