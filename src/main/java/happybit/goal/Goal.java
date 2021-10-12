package happybit.goal;

import happybit.habit.Habit;

import java.util.ArrayList;

public class Goal {

    protected String goalName;
    protected GoalType goalType;
    protected ArrayList<Habit> habitList = new ArrayList<>();

    /**
     * Constructor for Goal class with goalType defined.
     *
     * @param goalName String description of the goal.
     * @param goalType Type/Category of goal.
     */
    public Goal(String goalName, GoalType goalType) {
        this.goalName = goalName;
        this.goalType = goalType;
    }

    /**
     * Constructor for Goal class with goalType not defined.
     * goalType will be set as DEFAULT.
     *
     * @param goalName String description of the goal.
     */
    public Goal(String goalName) {
        this.goalName = goalName;
        this.goalType = GoalType.DEFAULT;
    }

    /**
     * Setter for name of goal.
     * Used to edit the name of the goal.
     *
     * @param goalName
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
        return getGoalTypeCharacter() + " " + goalName + "\n";
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
     * Gets the number of habits in a goal.
     *
     * @return Integer number of habits associated with the goal.
     */
    public int numberOfHabits() {
        return habitList.size();
    }

    /**
     * Prints list of habits associated with the goal.
     */
    public void printHabitList() {
        for (int i = 0; i < habitList.size(); i++) {
            System.out.println(i + ". " + habitList.get(i).getDescription() + "\n");
        }
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
    private String getGoalTypeCharacter() {
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