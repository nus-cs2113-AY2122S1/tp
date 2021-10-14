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
     * @param goalName Name of goal to be updated.
     */
    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    /**
     * Gets the description of the goal.
     *
     * @return String containing goal name and type.
     */
    public String getDescription() {
        return getGoalTypeCharacter() + " " + goalName;
    }

    /**
     * Gets the habitList.
     *
     * @return ArrayList of habits.
     */
    public ArrayList<Habit> getHabitList() {
        return habitList;
    }

    /**
     * Adds a habit to the goal.
     *
     * @param habit Habit to be added to the goal.
     */
    public void addHabit(Habit habit) {
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
        habit.setCompleted();
    }

    /**
     * Gets the number of habits in a goal.
     *
     * @return Integer number of habits associated with the goal.
     */
    public int numberOfHabits() {
        return habitList.size();
    }

    public String getStartDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

        return dateFormat.format(this.startDate);
    }

    public String getEndDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyyyy");

        return dateFormat.format(this.endDate);
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Gets the corresponding 2-character code for the goalType.
     *
     * @return String of the goalType 2-character code.
     */
    public String getGoalTypeCharacter() {
        switch (this.goalType) {
        case SLEEP:
            return "[SL]";
        case FOOD:
            return "[FD]";
        case EXERCISE:
            return "[EX]";
        case STUDY:
            return "[SD]";
        default:
            return "[DF]";
        }
    }
}