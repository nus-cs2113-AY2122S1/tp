package happybit.goal;

import happybit.habit.Habit;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Goal {

    protected String goalName;
    protected GoalType goalType;
    protected Date startDate;
    protected Date endDate;
    protected ArrayList<Habit> habitList;

    /**
     * Constructor for Goal class with goalType and startDate defined.
     *
     * @param goalName  String description of the goal.
     * @param goalType  Type/Category of goal.
     * @param startDate Date to start tracking of the goal.
     * @param endDate   Date to end tracking of the goal.
     */
    public Goal(String goalName, GoalType goalType, Date startDate, Date endDate) {
        this.goalName = goalName;
        this.goalType = goalType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.habitList = new ArrayList<>();
    }

    /**
     * Setter for name of goal.
     * Used to edit the name of the goal.
     *
     * @param goalName New name the goal is to be updated with.
     */
    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    /**
     * Getter for name of goal.
     *
     * @return Name of goal.
     */
    public String getGoalName() {
        return goalName;
    }

    /**
     * Getter for description of the goal.
     *
     * @return String containing goal name and type.
     */
    public String getDescription() {
        return getGoalType() + " " + goalName;
    }

    /**
     * Getter for startDate of the goal in string format. (For storage)
     *
     * @return Start date formatted as a string.
     */
    public String getStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        return dateFormat.format(this.startDate);
    }

    /**
     * Getter for startDate of the goal in string format. (For printing)
     *
     * @return Start date formatted as a string.
     */
    public String getPrintableStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormat.format(this.startDate);
    }

    /**
     * Getter for endDate of the goal in string format. (For storage)
     *
     * @return End date formatted as a string.
     */
    public String getEndDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");
        return dateFormat.format(this.endDate);
    }

    /**
     * Getter for endDate of the goal in string format. (For printing)
     *
     * @return End date formatted as a string.
     */
    public String getPrintableEndDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        return dateFormat.format(this.endDate);
    }

    /**
     * Getter for habitList.
     *
     * @return ArrayList of habits.
     */
    public ArrayList<Habit> getHabitList() {
        return habitList;
    }

    /**
     * Returns the size of the habitList.
     *
     * @return Size of habitList.
     */
    public int getHabitListSize() {
        return habitList.size();
    }

    /**
     * Adds a habit to the goal.
     * From user input, need to addProgress().
     *
     * @param habit Habit to be added to the goal.
     */
    public void addHabit(Habit habit) {
        habitList.add(habit);
        // get newly added habit and add progress
        Habit newHabit = habitList.get(getListLength() - 1);
        newHabit.addProgress();
    }

    /**
     * Adds a habit to the current goal.
     * From storage, no need to addProgress() as progress obtained from storage.
     *
     * @param habit Habit being added to habitList
     */
    public void addHabitFromStorage(Habit habit) {
        habitList.add(habit);
    }

    /**
     * Removes a habit from the goal.
     *
     * @param habitIndex Index corresponding to habit in the habitList.
     */
    public void removeHabit(int habitIndex) {
        habitList.remove(habitIndex);
    }

    /**
     * Sets a habit at user specified index as done.
     *
     * @param habitIndex Index corresponding to habit in the habitList
     */
    public void doneHabit(int habitIndex) {
        Habit habit = habitList.get(habitIndex);
        // update key value pair in map for current iteration
        habit.updateProgress();
    }

    /**
     * Gets the number of habits in a goal.
     *
     * @return Integer number of habits associated with the goal.
     */
    public int getListLength() {
        return habitList.size();
    }


    /**
     * Gets the corresponding 2-character code for the goalType.
     *
     * @return String of the goalType 2-character code.
     */
    public String getGoalType() {
        switch (this.goalType) {
        case SLEEP:
            return "Sleep";
        case FOOD:
            return "Food";
        case EXERCISE:
            return "Exercise";
        case STUDY:
            return "Study";
        default:
            return "Default";
        }
    }



    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

}