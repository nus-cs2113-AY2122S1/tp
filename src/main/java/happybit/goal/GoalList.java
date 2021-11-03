package happybit.goal;

import happybit.exception.HaBitCommandException;
import happybit.habit.Habit;
import happybit.interval.Interval;
import happybit.ui.PrintManager;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class GoalList {
    private static final String ERROR_EMPTY_GOAL_LIST = "There are no goals!";
    private static final String ERROR_INVALID_GOAL_INDEX = "There are no goals at that index.";
    private static final String ERROR_EMPTY_HABIT_LIST = "There are no habits listed under this goal!";
    private static final String ERROR_INVALID_HABIT_INDEX = "There are no habits at this index in your goal.";
    private static final String ERROR_IDENTICAL_NEW_NAME = "There is no change in name.";
    private static final String ERROR_IDENTICAL_NEW_TYPE = "There is no change in type.";
    private static final String ERROR_IDENTICAL_NEW_DATE = "There is no change in end date";
    private static final String ERROR_DUPLICATE_HABIT_NAME = "This habit name is already present for this goal";
    private static final String ERROR_DUPLICATE_GOAL_NAME = "This goal name is already present in your list";
    private static final String ERROR_NEW_END_DATE_AFTER_START_DATE = "You cannot have the end date of a goal before "
            + "or on the start date itself.";

    protected ArrayList<Goal> goalList;
    protected int chosenGoalIndex;

    /**
     * Constructor of GoalList.
     */
    public GoalList() {
        this.goalList = new ArrayList<>();
        this.chosenGoalIndex = -1;
    }

    /**
     * Getter for goalList.
     *
     * @return GoalList consisting of list of added goals.
     */
    public ArrayList<Goal> getGoalList() {
        return goalList;
    }

    /**
     * Getter for index of chosen goal.
     * To be used for goal quick view (not implemented).
     *
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
            goalType = this.getGoal(chosenGoalIndex).goalType;
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

    /**
     * Gets the number of items in the goal list.
     *
     * @return Number of items in the goal list.
     */
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
    public void addGoal(Goal goal, PrintManager printManager) throws HaBitCommandException {
        if (duplicateInGoalList(goal.getGoalName())) {
            throw new HaBitCommandException(ERROR_DUPLICATE_GOAL_NAME);
        }
        goalList.add(goal);
        printManager.printAddedGoal(goal.getDescription());
    }

    /**
     * Adds a habit that is linked to a goal.
     * From storage.
     *
     * @param habit     Habit to be linked to a goal.
     * @param goalIndex Integer index of goal in goalList.
     */
    public void addHabitToGoal(Habit habit, int goalIndex) throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        goal.addHabitFromStorage(habit);
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
        // check duplicate for currHabit
        if (goal.duplicateInHabitList(habit.getHabitName())) {
            throw new HaBitCommandException(ERROR_DUPLICATE_HABIT_NAME);
        }
        Habit newHabit = updateHabitEndDate(goal, habit);
        newHabit.populateIntervalsDuringHabitCreation();
        goal.addHabit(newHabit);
        printManager.printAddedHabit(newHabit.getHabitName(), goal.getDescription());
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
    public void deleteHabitFromGoal(int goalIndex, int habitIndex, PrintManager printManager)
            throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        ArrayList<Habit> habitList = goal.getHabitList();
        Habit habit = getHabit(habitList,habitIndex);
        goal.removeHabit(habitIndex);
        printManager.printRemovedHabit(goal.getDescription(), habit.getHabitName());
    }

    /**
     * Marks Progress for a goal as done.
     * new Date() was used since we assume that the habit can only be marked as completed on the day itself.
     *
     * @param goalIndex Integer index of goal in goal list
     * @param habitIndex Integer index of habit to be marked as done in goal
     * @param printManager User Interface class for printing Habit marked as done to output.
     * @throws HaBitCommandException If goalIndex is not within index range of goalList.
     *                               if habitIndex is not within index range of the habitList
     */
    public void doneHabitFromGoal(int goalIndex, int habitIndex, PrintManager printManager)
            throws HaBitCommandException {
        Goal goal = this.getGoal(goalIndex);
        ArrayList<Habit> habitList = goal.getHabitList();
        Habit habit = this.getHabit(habitList, habitIndex);
        goal.doneHabit(habitIndex);
        String[] strDates = habit.getDoneHabitDates(new Date());
        printManager.printDoneHabit(goal.getDescription(), habit.getHabitName(), strDates);
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
        int numOfHabits = goal.getListLength();
        if (habitList.isEmpty()) {
            throw new HaBitCommandException(ERROR_EMPTY_HABIT_LIST);
        }
        printManager.printHabitList(goal.getGoalName(), habitList, numOfHabits);
    }

    /**
     * Lists all the habits that can be marked as done but are not completed.
     *
     * @return Arraylist containing details of all the habits to be done.
     */
    public ArrayList<String> listDueHabits() {
        ArrayList<String> habitsToDoList = new ArrayList<>();
        for (int goalIndex = 0; goalIndex < getListLength(); goalIndex++) {
            for (String s : goalList.get(goalIndex).getDueHabits()) {
                habitsToDoList.add("G:" + (goalIndex + 1) + " " + s);
            }
        }
        return habitsToDoList;
    }

    /**
     * Changes and updates the name of a goal with a new name from user.
     *
     * @param goalIndex    Index of the goal in goalList.
     * @param newGoalName  New name user wants to change the goal to.
     * @param printManager Prints messages to the console.
     * @throws HaBitCommandException If the goalIndex is not within the range of the goalList.
     */
    public void updateGoalName(int goalIndex, String newGoalName, PrintManager printManager)
            throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        String oldGoalName = goal.getGoalName();

        compareNewNameWithOldName(oldGoalName, newGoalName);

        goal.setGoalName(newGoalName);
        goalList.set(goalIndex, goal);
        printManager.printUpdatedGoalName(oldGoalName, newGoalName);
    }

    public void updateGoalEndDate(int goalIndex, Date newDate, PrintManager printManager) throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        Date oldDate = goal.getEndDate();
        Date startDate = goal.getStartDate();
        // check if new end date same as previous end date
        compareOldDateWithNewDate(oldDate, newDate);
        // check if new end date before start date
        checkNewDateAfterStartDate(startDate, newDate);
        goal.setEndDate(newDate);
        // Go through all habits for Goal
        // change endDate for all of them + call updateIntervals
        ArrayList<Habit> habits = goal.getHabitList();
        for (Habit habit : habits) {
            habit.setEndDate(newDate);
            // update Interval Lengths using same interval for habit
            habit.updateLengthOfInterval(habit.getIntervalLength());
        }
        String oldDateString = dateToString(oldDate);
        String goalName = goal.getGoalName();
        String newDateString = goal.getPrintableEndDate();
        printManager.printUpdatedGoalEndDate(goalName, oldDateString, newDateString);
    }

    public void updateGoalType(int goalIndex, GoalType newGoalType, PrintManager printManager)
            throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        GoalType oldGoalType = goal.getGoalType();
        final String oldGoalTypeName = goal.getGoalTypeStr();

        compareNewTypeWithOldType(oldGoalType, newGoalType);

        goal.setGoalType(newGoalType);
        goalList.set(goalIndex, goal);
        final String newGoalTypeName = goal.getGoalTypeStr();
        printManager.printUpdatedGoalType(oldGoalTypeName, newGoalTypeName);
    }

    /**
     * Changes and updates the name of a habit with a new name from user.
     *
     * @param goalIndex    Index of the goal in goalList.
     * @param habitIndex   Index of the habit in goal.
     * @param printManager Prints messages to the console.
     * @param newHabitName New name user wants to change the habit to.
     * @throws HaBitCommandException If the goalIndex and/or habitIndex is not within its respective list range.
     */
    public void updateHabitNameFromGoal(int goalIndex, int habitIndex, String newHabitName, PrintManager printManager)
            throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        ArrayList<Habit> habitList = goal.getHabitList();
        Habit habit = getHabit(habitList, habitIndex);
        String oldHabitName = habit.getHabitName();

        compareNewNameWithOldName(oldHabitName, newHabitName);

        habit.setHabitName(newHabitName);
        habitList.set(habitIndex, habit);
        printManager.printUpdatedHabitName(goal.getGoalName(), oldHabitName, newHabitName);
    }

    /**
     * Changes interval of a habit previously set by the user.
     * Need to check and update habitDate as well when executing this command.
     *
     * @param goalIndex Integer of goal index habit to update is under
     * @param habitIndex Integer of habit index of habit to update
     * @param newInterval Integer of new interval uses wishes to set
     */
    public void updateHabitIntervalFromGoal(int goalIndex, int habitIndex, int newInterval, PrintManager printManager)
        throws HaBitCommandException {
        Goal goal = getGoal(goalIndex);
        ArrayList<Habit> habits = goal.getHabitList();
        Habit habit = getHabit(habits, habitIndex);
        habit.updateLengthOfInterval(newInterval);
        printManager.printUpdatedHabitInterval(goal.getGoalName(), habit.getHabitName(), newInterval);
    }


    public void addIntervalToHabit(int goalIndex, int habitIndex, Interval interval) throws HaBitCommandException {
        Goal goal = this.getGoal(goalIndex);
        Habit habit = this.getHabit(goal.getHabitList(), habitIndex);
        habit.addInterval(interval);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Gets a goal from the goalList.
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
     * Gets a habit from the habitList of a goal.
     *
     * @param habitList  List of habits of a goal.
     * @param habitIndex Index of habit in habitList.
     * @return Habit from habitList corresponding to habitIndex.
     * @throws HaBitCommandException If the habitIndex provided is invalid.
     */
    private Habit getHabit(ArrayList<Habit> habitList, int habitIndex) throws HaBitCommandException {
        Habit habit;
        try {
            habit = habitList.get(habitIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new HaBitCommandException(ERROR_INVALID_HABIT_INDEX);
        }
        return habit;
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

    /**
     * Sets the endDate of the habit to be similar to the goal.
     *
     * @param goal  Goal that a habit is to be added to.
     * @param habit Habit to be added to the goal.
     * @return Habit with its endDate updated.
     */
    private Habit updateHabitEndDate(Goal goal, Habit habit) {
        Date goalEndDate = goal.getEndDate();
        habit.setEndDate(goalEndDate);
        return habit;
    }

    /**
     * Checks new name given by user with old name to ensure that they are different.
     * Throws an exception if they are the same (what are you even doing user.)
     *
     * @param oldName Current name of goal or habit.
     * @param newName New goal or habit name given by user.
     * @throws HaBitCommandException If old and new names are identical.
     */
    private void compareNewNameWithOldName(String oldName,String newName) throws HaBitCommandException {
        if (oldName.equals(newName)) {
            throw new HaBitCommandException(ERROR_IDENTICAL_NEW_NAME);
        }
    }

    /**
     * Checks name of new goal being added by user to ensure it is not present in their goal list.
     *
     * @param newType Type of new goal being added in
     * @return True if name already exists, False otherwise
     */
    private void compareNewTypeWithOldType(GoalType oldType, GoalType newType) throws HaBitCommandException {
        if (newType == oldType) {
            throw new HaBitCommandException(ERROR_IDENTICAL_NEW_TYPE);
        }
    }

    /**
     * Checks if new end Date set by user is the same as the old end Date for that goal.
     *
     * @param oldDate Current end Date for goal
     * @param newDate New end Date for goal
     * @throws HaBitCommandException thrown if they are oldDate and newDate are the same
     */
    private void compareOldDateWithNewDate(Date oldDate, Date newDate) throws HaBitCommandException {
        LocalDate oldDateLD = convertDateToLocalDate(oldDate);
        LocalDate newDateLD = convertDateToLocalDate(newDate);

        if (oldDateLD.isEqual(newDateLD)) {
            throw new HaBitCommandException(ERROR_IDENTICAL_NEW_DATE);
        }
    }

    private void checkNewDateAfterStartDate(Date startDate, Date newDate) throws HaBitCommandException {
        LocalDate startDateLD = convertDateToLocalDate(startDate);
        LocalDate newDateLD = convertDateToLocalDate(newDate);

        if (!newDateLD.isAfter(startDateLD)) {
            throw new HaBitCommandException(ERROR_NEW_END_DATE_AFTER_START_DATE);
        }
    }

    /**
     * 'Type-casting' a Date to a LocalDate.
     *
     * @param date Date to be 'type-casted'.
     * @return LocalDate that has been 'type-casted' from Date.
     */
    private LocalDate convertDateToLocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * 'Type-casting' a LocalDate to a Date.
     *
     * @param localDate LocalDate to be 'type-casted'.
     * @return Date that has been 'type-casted' from LocalDate.
     */
    private Date convertLocalDateToDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }

    private boolean duplicateInGoalList(String newName) {
        for (Goal goal : goalList) {
            if (goal.getGoalName().equals(newName)) {
                return true;
            }
        }
        return false;
    }

    private String dateToString(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormat.format(date);
    }
}
