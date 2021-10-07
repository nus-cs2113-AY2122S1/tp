package happybit.ui;

import java.util.ArrayList;

public class Ui {
    private static final String COMMAND_LIST_GREETING =
            "Hello! These are all the possible commands for this habit tracker :)";
    private static final String ADD_HABIT_COMMAND =
            "add a habit: add <habit name>";
    private static final String DELETE_HABIT_COMMAND =
            "delete a habit: delete <habit name>";
    private static final String SET_GOAL_COMMAND =
            "set a goal for a habit: set <habit name> <goal name>";
    private static final String REMOVE_GOAL_COMMAND =
            "remove a goal for a habit: remove <habit name> <goal name>";
    private static final String LIST_HABIT_COMMAND =
            "list all habits user has input: list";
    private static final String LIST_GOAL_COMMAND =
            "list all goals for that habit: list -<habit name>";
    private static final String NEWLINE = System.lineSeparator();
    private static final String DASHES = "______________________________________________________________"
            + "__________________________________________________________";

    public static void printCommandList() {
        printDashes();
        System.out.print(COMMAND_LIST_GREETING + NEWLINE
                + ADD_HABIT_COMMAND + NEWLINE
                + DELETE_HABIT_COMMAND + NEWLINE
                + SET_GOAL_COMMAND + NEWLINE
                + REMOVE_GOAL_COMMAND + NEWLINE
                + LIST_HABIT_COMMAND + NEWLINE
                + LIST_GOAL_COMMAND + NEWLINE
        );
        printDashes();
    }

    public static void printHabitsList(ArrayList<String> habits) {
        printDashes();
        for (String habit : habits) {
            System.out.println(habit);
        }
        printDashes();
    }

    private static void printDashes() {
        System.out.println(DASHES);
    }
}
