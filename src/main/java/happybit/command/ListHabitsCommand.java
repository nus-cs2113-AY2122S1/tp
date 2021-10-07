package happybit.command;

import happybit.ui.Ui;

import java.util.ArrayList;

public class ListHabitsCommand {

    // ArrayList for testing purposes
    private static ArrayList<String> habits = new ArrayList<>();

    /**
     * Lists out all habits user input
     */
    public static void runCommand() {
        // Add here to test whether printing works
        habits.add("Habit 1");
        habits.add("Habit 2");
        habits.add("Habit 3");
        habits.add("Habit 4");
        Ui.printHabitsList(habits);
    }
}
