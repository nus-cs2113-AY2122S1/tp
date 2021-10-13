package happybit.ui;

import happybit.goal.Goal;
import happybit.habit.Habit;

import java.util.ArrayList;

public class Ui {
    private static final String COMMAND_LIST_GREETING =
            "Hello! These are all the possible commands for this habit tracker :)";
    private static final String ADD_HABIT_COMMAND =
            "add a habit: add <habit type> <habit name>";
    private static final String HABIT_TYPE_INFO =
            "-> Habit types include: default, sleep, food, exercise and study";
    private static final String DELETE_HABIT_COMMAND =
            "delete a habit: delete <habit name>";
    private static final String SET_GOAL_COMMAND =
            "set a goal for a habit: set <habit name> <goal name> /<start date> - /<end date>";
    private static final String REMOVE_GOAL_COMMAND =
            "remove a goal for a habit: remove <habit name> <goal name>";
    private static final String LIST_HABIT_COMMAND =
            "list all habits user has input: list";
    private static final String LIST_GOAL_COMMAND =
            "list all goals for that habit: list -<habit name>";
    private static final String NEWLINE = System.lineSeparator();
    private static final String DASHES = "______________________________________________________________"
            + "__________________________________________________________";

    public void printCommandList() {
        printDashes();
        System.out.print(COMMAND_LIST_GREETING + NEWLINE
                + ADD_HABIT_COMMAND + NEWLINE
                + HABIT_TYPE_INFO + NEWLINE
                + DELETE_HABIT_COMMAND + NEWLINE
                + SET_GOAL_COMMAND + NEWLINE
                + REMOVE_GOAL_COMMAND + NEWLINE
                + LIST_HABIT_COMMAND + NEWLINE
                + LIST_GOAL_COMMAND + NEWLINE
        );
        printDashes();
    }

    public void printGoalList(ArrayList<Goal> goals) {
        printDashes();
        for (Goal goal : goals) {
            System.out.println(goal.getDescription());
        }
        printDashes();
    }

    public void printHabitList(ArrayList<Habit> habits) {
        printDashes();
        for (Habit habit : habits) {
            System.out.println(habit.getHabitName());
        }
        printDashes();
    }

    public static void printRemovedGoal(String goalDescription) {
        printDashes();
        System.out.println("Your goal: " + goalDescription + "has been removed!");
        printDashes();
    }

    private static void printDashes() {
        System.out.println(DASHES);
    }

    public static void showWelcome() {
        printDashes();
        System.out.println("Howdy! Welcome to Ha(ppy)Bit!");
        printDashes();
    }

    public static void showGoodbye() {
        printDashes();
        System.out.println("Thanks for using Ha(ppy)Bit, see you in a \033[3mbit\033[0m! (hehe)" + NEWLINE);
        System.out.println("\"We are what we repeatedly do. Excellence, then, is not an act, but a habit.\"" + NEWLINE
                + " — Will Durant");
        printDashes();
    }
}
