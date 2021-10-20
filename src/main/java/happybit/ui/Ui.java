package happybit.ui;

import happybit.goal.Goal;
import happybit.habit.Habit;

import java.util.ArrayList;

public class Ui {
    private static final String COMMAND_LIST_GREETING =
            "Hello! These are all the possible commands for this habit tracker :)"
                    + " (flags within {} brackets are optional)";
    private static final String SET_GOAL_COMMAND =
            "1. set a goal: set <goal name> {-<goal type> /<start date>} /<end date>";
    private static final String GOAL_TYPE_INFO =
            "   -> Goal types include: default, sleep, food, exercise and study";
    private static final String REMOVE_GOAL_COMMAND =
            "2. remove a goal: remove <goal index>";
    private static final String LIST_GOAL_COMMAND =
            "3. list all goals for that habit: list";
    private static final String ADD_HABIT_COMMAND =
            "4. add a habit to a goal: add <goal index> <habit name>";
    private static final String DELETE_HABIT_COMMAND =
            "5. delete a habit from a goal: delete <goal index> <habit index>";
    private static final String DONE_HABIT_COMMAND =
            "6. indicate a habit as done: done <goal index> <habit index>";
    private static final String LIST_HABIT_COMMAND =
            "7. View all the habits user has under a goal: view <goal index>";
    private static final String BYE_COMMAND = "8. Exit habit tracker program: bye";

    private static final String NEWLINE = System.lineSeparator();
    private static final String DASHES = "______________________________________________________________"
            + "__________________________________________________________";

    public void printCommandList() {
        printDashes();
        System.out.print(COMMAND_LIST_GREETING + NEWLINE
                + SET_GOAL_COMMAND + NEWLINE
                + GOAL_TYPE_INFO + NEWLINE
                + REMOVE_GOAL_COMMAND + NEWLINE
                + LIST_GOAL_COMMAND + NEWLINE
                + ADD_HABIT_COMMAND + NEWLINE
                + DELETE_HABIT_COMMAND + NEWLINE
                + DONE_HABIT_COMMAND + NEWLINE
                + LIST_HABIT_COMMAND + NEWLINE
                + BYE_COMMAND + NEWLINE
        );
        printDashes();
    }

    public void printGoalList(ArrayList<Goal> goals, int numOfGoals) {
        printDashes();
        assert (numOfGoals > 0) : "List cannot be empty here";
        System.out.println("There is/are " + numOfGoals + " goal(s) in your list:");
        for (Goal goal : goals) {
            System.out.println(goal.getDescription());
        }
        printDashes();
    }

    public void printHabitList(String goalDescription, ArrayList<Habit> habits, int numOfHabits) {
        printDashes();
        assert (numOfHabits > 0) : "List cannot be empty here";
        System.out.println("Here are your " + numOfHabits + " habit(s) under the goal \""
                + goalDescription + "\".");
        String prefix = "[ ]";
        for (Habit habit : habits) {
            if (habit.getDone()) {
                prefix = "[X]";
            }
            System.out.println(prefix + " " + habit.getHabitName());
        }
        printDashes();
    }

    public void printAddedGoal(String goalDescription) {
        printDashes();
        System.out.println("Your goal: " + goalDescription + " has been added.");
        printDashes();
    }

    public void printAddedHabit(String habitDescription, String goalDescription) {
        printDashes();
        System.out.println("Your habit: " + habitDescription + " has been added to your goal: " + goalDescription);
        printDashes();
    }

    public void printRemovedGoal(String goalDescription) {
        printDashes();
        System.out.println("Your goal: " + goalDescription + " has been removed.");
        printDashes();
    }

    public void printRemovedHabit(String goalDescription, String habitName) {
        printDashes();
        System.out.println("Your habit of \"" + habitName + "\" under the goal \""
                + goalDescription + "\" has been deleted.");
        printDashes();
    }

    public void printDoneHabit(String goalDescription, String habitName) {
        printDashes();
        System.out.println("Your habit of \"" + habitName + "\" under the goal \""
                + goalDescription + "\" has been set as done.");
        printDashes();
    }

    public void printUpdatedGoal(String oldGoalDescription, String goalDescription) {
        printDashes();
        System.out.println("Your goal \"" + oldGoalDescription + "\" has been changed to \"" + goalDescription + "\".");
        printDashes();
    }

    public void showWelcome() {
        printDashes();
        System.out.println("Howdy! Welcome to Ha(ppy)Bit!");
        printDashes();
    }

    public void showError(String message) {
        printDashes();
        System.out.println(message);
        printDashes();
    }

    public void showGoodbye() {
        printDashes();
        System.out.println("Thanks for using Ha(ppy)Bit, see you in a \033[3mbit\033[0m! (hehe)" + NEWLINE);
        System.out.println("\"We are what we repeatedly do. Excellence, then, is not an act, but a habit.\"" + NEWLINE
                + " — Will Durant");
        printDashes();
    }

    private void printDashes() {
        System.out.println(DASHES);
    }

}
