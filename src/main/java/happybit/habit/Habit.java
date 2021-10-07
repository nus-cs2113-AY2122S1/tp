package happybit.habit;

import happybit.goal.Goal;

import java.util.ArrayList;

public class Habit {

    protected String habitName;
    protected HabitType habitType;
    protected ArrayList<Goal> goalList = new ArrayList<>();

    /**
     * Constructor for Goal class with habitType defined.
     *
     * @param habitName String description of the habit.
     * @param habitType Type/Category of habit.
     */
    public Habit(String habitName, HabitType habitType) {
        this.habitName = habitName;
        this.habitType = habitType;
    }

    /**
     * Constructor for Goal class with habitType not defined.
     * habitType will be set as DEFAULT.
     *
     * @param habitName String description of the habit.
     */
    public Habit(String habitName) {
        this.habitName = habitName;
        this.habitType = HabitType.DEFAULT;
    }

    /**
     * Getter for habitName.
     *
     * @return String description of the habit.
     */
    public String getHabitName() {
        return habitName;
    }

    /**
     * Getter for habitType.
     *
     * @return Type/Category of habit.
     */
    public HabitType getHabitType() {
        return habitType;
    }

    /**
     * Gets the description of the habit.
     *
     * @return String containing habit name and type.
     */
    public String getDescription() {
        return getHabitTypeCharacter() + " " + habitName + "\n";
    }

    /**
     * Adds a goal to the habit.
     *
     * @param goal Goal to be added to the habit.
     */
    public void addGoal(Goal goal) {
        goalList.add(goal);
    }

    /**
     * Gets the number of goals in a habit.
     *
     * @return Integer number of goals associated with the habit.
     */
    public int numberOfGoals() {
        return goalList.size();
    }

    /**
     * Prints list of goals associated with the habit.
     */
    public void printGoalList() {
        for (int i = 0; i < goalList.size(); i++) {
            System.out.println(i + ". " + goalList.get(i).getDescription() + "\n");
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
     * Gets the corresponding 2-character code for the habitType.
     *
     * @return String of the habitType 2-character code.
     */
    private String getHabitTypeCharacter() {
        switch (this.habitType) {
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
