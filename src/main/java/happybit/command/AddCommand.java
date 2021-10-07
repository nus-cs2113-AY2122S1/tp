package happybit.command;

import java.util.ArrayList;

public class AddCommand {
    // temporary attribute
    private final ArrayList<String> habits = new ArrayList<>();

    /**
     * Adds habit to habit list.
     *
     * @param habit Habit to add to habit list
     */
    public void addHabit(String habit) {
        habits.add(habit);
    }

    /**
     * Adds a habit.
     */
    public void runCommand(String habit) {
        addHabit(habit);
    }

    /**
     * Returns size of habits list.
     *
     * @return Size of habits list
     */
    public int getHabitListSize() {
        return habits.size();
    }

    /**
     * Returns list of all current habits
     *
     * @return List of all current habits
     */
    public ArrayList<String> getHabits() {
        return habits;
    }
}
