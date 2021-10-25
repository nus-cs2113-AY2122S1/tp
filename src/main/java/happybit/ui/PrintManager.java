package happybit.ui;

import happybit.goal.Goal;
import happybit.habit.Habit;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PrintManager {
    private static final String COMMAND_LIST_GREETING =
            "Hello! These are all the possible commands for this habit tracker :)"
                    + " (flags within {} brackets are optional)";
    private static final String SET_GOAL_COMMAND =
            "- set a goal: set n/<goal name> { t/<goal type> s/<start date> } e/<end date>";
    private static final String GOAL_TYPE_INFO =
            "   -> Goal types include: default[df], sleep[sl], food[fd], exercise[ex] and study[sd]";
    private static final String UPDATE_GOAL_COMMAND =
            "- update a goal: update g/<goal index> n/<new goal name>";
    private static final String REMOVE_GOAL_COMMAND =
            "- remove a goal: remove g/<goal index>";
    private static final String LIST_GOAL_COMMAND =
            "- list all goals for that habit: list";
    private static final String ADD_HABIT_COMMAND =
            "- add a habit to a goal: add g/<goal index> n/<habit name> i/<interval>";
    private static final String DELETE_HABIT_COMMAND =
            "- delete a habit from a goal: delete g/<goal index> h/<habit index>";
    private static final String DONE_HABIT_COMMAND =
            "- indicate a habit as done: done g/<goal index> h/<habit index>";
    private static final String LIST_HABIT_COMMAND =
            "- View all the habits user has under a goal: view g/<goal index>";
    private static final String BYE_COMMAND = "- Exit habit tracker program: bye";

    private static final String NEWLINE = System.lineSeparator();
    private static final String DASHES = "______________________________________________________________"
            + "__________________________________________________________";

    public void printCommandList() {
        printDashes();
        System.out.print(COMMAND_LIST_GREETING + NEWLINE
                + SET_GOAL_COMMAND + NEWLINE
                + GOAL_TYPE_INFO + NEWLINE
                + UPDATE_GOAL_COMMAND + NEWLINE
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
        int index = 1;
        printDashes();
        assert (numOfGoals > 0) : "List cannot be empty here";
        System.out.println("There is/are " + numOfGoals + " goal(s) in your list:");
        for (Goal goal : goals) {
            System.out.print(index + ". ");
            System.out.println(goal.getDescription());
            index++;
        }
        printDashes();
    }

    public void printHabitList(String goalDescription, ArrayList<Habit> habits, int numOfHabits) {
        int index = 1;
        printDashes();
        assert (numOfHabits > 0) : "List cannot be empty here";
        System.out.println("Here are your " + numOfHabits + " habit(s) under the goal \""
                + goalDescription + "\".");
        for (Habit habit : habits) {
            String currIndex = index + ". ";
            printHabitDetails(habit, currIndex);
            index++;
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

    public void printDoneHabit(String goalDescription, Habit habit) {
        printDashes();
        System.out.println("You have completed your habit of \"" + habit.getHabitName() + "\" under the goal \""
                + goalDescription + "\" set for " + habit.getHabitDateString() + ". Well Done!");
        if (habit.getInterval() > 0) {
            System.out.println("Your next date set for this habit is " + habit.getNextHabitDateString());
        } else {
            System.out.println("Update the habit with a regular interval value to make it recurring!");
        }
        printDashes();
    }

    public void printUpdatedGoal(String oldGoalDescription, String goalDescription) {
        printDashes();
        System.out.println("Your goal \"" + oldGoalDescription + "\" has been changed to \"" + goalDescription + "\".");
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

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    private void printHabitDetails(Habit habit, String currIndex) {
        String intervalPrint = "";
        int habitIntervals = habit.getInterval();
        if (habitIntervals > 0) {
            intervalPrint ="(every " + habit.getInterval() + " day(s)";
        }
        Date lastHabitDate = habit.getHabitDate();
        Date nextHabitDate = habit.getNextHabitDate();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("dd/MM/yyyy");
        String lastHabitDatePrint = dateFormatter.format(lastHabitDate);
        String nextHabitDatePrint = dateFormatter.format(nextHabitDate);
        System.out.println(currIndex + " " + habit.getHabitName() + " " + intervalPrint);
        System.out.println("Last: " + lastHabitDatePrint + ", " + "Next: " + nextHabitDatePrint);
    }

    private void printDashes() {
        System.out.println(DASHES);
    }

}
