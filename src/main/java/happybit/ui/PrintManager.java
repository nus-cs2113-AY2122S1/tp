package happybit.ui;

import happybit.goal.Goal;
import happybit.habit.Habit;

import java.util.ArrayList;

public class PrintManager {

    private static final String LS = System.lineSeparator();
    private static final String PERCENT_SYMBOL = "%";
    private static final String LINE = "______________________________________________________________"
            + "__________________________________________________________";

    private static final String MSG_ERROR = "Error Detected: %1$s" + LS;
    private static final String MSG_LIST_COMMAND = "Here are the list of commands:";
    private static final String MSG_LIST_GOAL = "%1$s goal(s) currently being tracked:" + LS;
    private static final String MSG_LIST_HABIT = "%1$s habit(s) currently being tracked for %2$s:" + LS;
    private static final String MSG_ADD_GOAL = "The goal '%1$s' has been added." + LS;
    private static final String MSG_ADD_HABIT = "The habit '%1$s' has been added to goal '%2$s'" + LS;
    private static final String MSG_DELETE_GOAL = "The goal '%1$s' has been removed." + LS;
    private static final String MSG_DELETE_HABIT = "The habit '%1$s' of goal '%2$s' has been deleted." + LS;
    private static final String MSG_DONE_HABIT = "The habit '%1$s' of goal '%2$s' has been completed for %3$s to %4$s."
            + LS + "The next interval will begin on %5$s" + LS;
    private static final String MSG_UPDATE_GOAL_NAME = "The goal name '%1$s' has been updated to '%2$s'." + LS;
    private static final String MSG_UPDATE_GOAL_TYPE = "The goal type '%1$s' has been updated to '%2$s'." + LS;
    private static final String MSG_UPDATE_GOAL_END_DATE = "The goal end date of goal '%1$s' has been changed from "
            + "'%2$s' to '%3$s'." + LS;
    private static final String MSG_UPDATE_HABIT_NAME = "The habit '%1$s' of goal '%2$s' has been changed to '%3$s'"
            + LS;
    private static final String MSG_UPDATE_HABIT_INTERVAL = "The habit '%1$s' of goal '%2$s' has its interval changed "
            + "to '%3$s'" + LS;
    private static final String MSG_EXIT = "Thanks for using Ha(ppy)Bit, see you in a \033[3mbit\033[0m! (hehe)" + LS
            + "\"We are what we repeatedly do. Excellence, then, is not an act, but a habit.\"" + LS
            + " — Will Durant";

    private static final String COMMAND_HELP = "open command list";
    private static final String COMMAND_ADD_GOAL = "set a goal";
    private static final String COMMAND_ADD_HABIT = "add a habit";
    private static final String COMMAND_UPDATE_GOAL = "update a goal name/type/end date";
    private static final String COMMAND_UPDATE_HABIT = "change a habit name/interval";
    private static final String COMMAND_LIST_GOAL = "list all goals";
    private static final String COMMAND_LIST_HABIT = "view all habits of a goal";
    private static final String COMMAND_DELETE_GOAL = "remove a goal";
    private static final String COMMAND_DELETE_HABIT = "delete a habit";
    private static final String COMMAND_DONE_HABIT = "mark a habit as done";
    private static final String COMMAND_RETURN = "return to start screen";
    private static final String COMMAND_EXIT = "exit the application";

    private static final String INSTR_HELP = "help";
    private static final String INSTR_ADD_GOAL = "set n/<goal_name> { t/<goal_type> s/<start_date> } e/<end_date>";
    private static final String INSTR_ADD_HABIT = "add g/<goal_index> n/<habit_name> i/<interval>";
    private static final String INSTR_UPDATE_GOAL = "update g/<goal_index> [ n/<goal_name> t/<goal_type> "
            + "e/<end_date> ]";
    private static final String INSTR_UPDATE_HABIT = "change g/<goal_index> h/<habit_index> [ n/<habit_name> "
            + "i/<interval> ]";
    private static final String INSTR_LIST_GOAL = "list";
    private static final String INSTR_LIST_HABIT = "view g/<goal_index>";
    private static final String INSTR_DELETE_GOAL = "remove g/<goal_index>";
    private static final String INSTR_DELETE_HABIT = "delete g/<goal_index> h/<habit_index>";
    private static final String INSTR_DONE_HABIT = "done g/<goal_index> h/<habit_index>";
    private static final String INSTR_RETURN = "return";
    private static final String INSTR_EXIT = "exit";

    private static final String COMMAND_NOTE_1 = "* Replace <> with relevant terms (Exp: <goal_name> --> goal 1)";
    private static final String COMMAND_NOTE_2 = "* Items enclosed within { } are optional";
    private static final String COMMAND_NOTE_3 = "* Items enclosed within [ ] need to have at least one of the "
            + "parameters specified inside.";
    private static final String COMMAND_NOTE_4 = "* Use 'list' and 'view' to check the goal and habit indexes "
            + "respectively";

    private static final String[] COMMAND_HEADERS = {"Action", "Format"};
    private static final String[] GOAL_HEADERS = {"Index", "Name", "Type", "Start Date", "End Date", "Habit Count",
        "Completion Rate"};
    private static final String[] HABIT_HEADERS = {"Index", "Name", "Interval", "Completion", "Completed", "Remaining",
        "Expired", "Streak"};

    private static final int INDEX_INDEX = 0;
    private static final int GOAL_NAME_INDEX = 1;
    private static final int GOAL_TYPE_INDEX = 2;
    private static final int GOAL_START_DATE_INDEX = 3;
    private static final int GOAL_END_DATE_INDEX = 4;
    private static final int GOAL_HABIT_NUM_INDEX = 5;
    private static final int GOAL_COMPLETION_RATE_INDEX = 6;
    private static final int HABIT_NAME_INDEX = 1;
    private static final int HABIT_INTERVAL_INDEX = 2;
    private static final int HABIT_COMPLETION_RATE_INDEX = 3;
    private static final int HABIT_COMPLETED_INDEX = 4;
    private static final int HABIT_REMAINING_INDEX = 5;
    private static final int HABIT_EXPIRED_INDEX = 6;
    private static final int HABIT_STREAK_INDEX = 7;
    private static final int COMPLETION_RATE_INDEX = 0;
    private static final int COMPLETED_INDEX = 1;
    private static final int REMAINING_INDEX = 2;
    private static final int EXPIRED_INDEX = 3;
    private static final int COMMAND_INDEX = 0;
    private static final int INSTR_INDEX = 1;

    /**
     * Prints the list of commands.
     */
    public void printCommandList() {
        System.out.println(MSG_LIST_COMMAND);
        PrintTable.printTable(COMMAND_HEADERS, populateCommandData());
        System.out.println(COMMAND_NOTE_1 + LS + COMMAND_NOTE_2 + LS + COMMAND_NOTE_3 + LS + COMMAND_NOTE_4);
    }

    /**
     * Prints the list of goals in the goalList.
     *
     * @param goals      List of goals.
     * @param numOfGoals Number of goals in the goal list.
     */
    public void printGoalList(ArrayList<Goal> goals, int numOfGoals) {
        String[][] data = populateGoalData(goals, numOfGoals, GOAL_HEADERS.length);
        System.out.printf(MSG_LIST_GOAL, numOfGoals);
        PrintTable.printTable(GOAL_HEADERS, data);
    }

    /**
     * Prints the list of habits in the habitList.
     *
     * @param goalDescription String of goal name and type.
     * @param habits          List of habits.
     * @param numOfHabits     Number of habits in the habitList.
     */
    public void printHabitList(String goalDescription, ArrayList<Habit> habits, int numOfHabits) {
        String[][] data = populateHabitData(habits, numOfHabits, HABIT_HEADERS.length);
        System.out.printf(MSG_LIST_HABIT, numOfHabits, goalDescription);
        PrintTable.printTable(HABIT_HEADERS, data);
    }

    /**
     * Prints a confirmation message upon successful addition of new goal.
     *
     * @param goalDescription String of goal name and type.
     */
    public void printAddedGoal(String goalDescription) {
        printLine();
        System.out.printf(MSG_ADD_GOAL, goalDescription);
        printLine();
    }

    /**
     * Prints a confirmation message upon successful addition of new habit.
     *
     * @param habitDescription String of habit name.
     * @param goalDescription  String of goal name and type.
     */
    public void printAddedHabit(String habitDescription, String goalDescription) {
        printLine();
        System.out.printf(MSG_ADD_HABIT, habitDescription, goalDescription);
        printLine();
    }

    /**
     * Prints a confirmation message upon successful deletion of a goal.
     *
     * @param goalDescription String of goal name and type.
     */
    public void printRemovedGoal(String goalDescription) {
        printLine();
        System.out.printf(MSG_DELETE_GOAL, goalDescription);
        printLine();
    }

    /**
     * Prints a confirmation message upon successful deletion of a habit.
     *
     * @param goalDescription  String of goal name and type.
     * @param habitDescription String of habit name.
     */
    public void printRemovedHabit(String goalDescription, String habitDescription) {
        printLine();
        System.out.printf(MSG_DELETE_HABIT, habitDescription, goalDescription);
        printLine();
    }

    /**
     * Prints a confirmation message upon successful completion of a habit.
     *
     * @param goalDescription  String of goal name and type.
     * @param habitDescription String of habit name.
     * @param strDates         String array of dates containing startDate, endDate and next startDate.
     */
    public void printDoneHabit(String goalDescription, String habitDescription, String[] strDates) {
        printLine();
        System.out.printf(MSG_DONE_HABIT, habitDescription, goalDescription, strDates[0], strDates[1], strDates[2]);
        printLine();
    }

    /**
     * Prints a confirmation message upon successful update of a goal name.
     * Note: Only the goal name has been changed.
     *
     * @param oldGoalDescription String of previous goal name and type.
     * @param newGoalDescription String of updated goal name and type.
     */
    public void printUpdatedGoalName(String oldGoalDescription, String newGoalDescription) {
        printLine();
        System.out.printf(MSG_UPDATE_GOAL_NAME, oldGoalDescription, newGoalDescription);
        printLine();
    }

    public void printUpdatedGoalType(String oldGoalTypeName, String newGoalTypeName) {
        printLine();
        System.out.printf(MSG_UPDATE_GOAL_TYPE, oldGoalTypeName, newGoalTypeName);
        printLine();
    }

    public void printUpdatedGoalEndDate(String goalName, String oldGoalEndDate, String newGoalEndDate) {
        printLine();
        System.out.printf(MSG_UPDATE_GOAL_END_DATE, goalName, oldGoalEndDate, newGoalEndDate);
        printLine();
    }

    /**
     * Prints a confirmation message upon successful update of a habit name.
     *
     * @param goalDescription     String of goal name and type.
     * @param oldHabitDescription String of previous habit name.
     * @param newHabitDescription String of updated habit name.
     */
    public void printUpdatedHabitName(String goalDescription, String oldHabitDescription, String newHabitDescription) {
        printLine();
        System.out.printf(MSG_UPDATE_HABIT_NAME, oldHabitDescription, goalDescription, newHabitDescription);
        printLine();
    }

    /**
     * Prints a confirmation message upon successful update of a habit interval.
     *
     * @param goalDescription  String of goal name and type.
     * @param habitDescription String of habit name.
     * @param interval         New interval length.
     */
    public void printUpdatedHabitInterval(String goalDescription, String habitDescription, int interval) {
        printLine();
        System.out.printf(MSG_UPDATE_HABIT_INTERVAL, habitDescription, goalDescription, interval);
        printLine();
    }

    public void printStorageMessage(String storageMessage) {
        System.out.println(storageMessage);
    }

    /**
     * Prints an error message.
     *
     * @param message String containing the error message.
     */
    public void printError(String message) {
        printLine();
        System.out.printf(MSG_ERROR, message);
        printLine();
    }

    /**
     * Prints an exit message.
     */
    public void printExit() {
        printLine();
        System.out.println(MSG_EXIT);
        printLine();
    }

    /*
     * NOTE : ==================================================================
     * The following are private methods that are used to implement SLAP for the
     * above public methods. These methods are positioned at the bottom to better
     * visualise the actual methods that can be called from outside this class.
     * =========================================================================
     */

    /**
     * Prints a horizontal line.
     * Used for enclosing message within 2 such lines.
     */
    private void printLine() {
        System.out.println(LINE);
    }

    /* The following are sub-methods of list printing.
     * populateGoalData()
     * populateHabitData()
     * populateCommandData()
     */

    /**
     * Populate a 2D array with goal data.
     *
     * @param goals        Arraylist containing all the goals.
     * @param numOfGoals   Number of goals.
     * @param numOfColumns Number of data columns.
     * @return 2D string array containing goal data.
     */
    private String[][] populateGoalData(ArrayList<Goal> goals, int numOfGoals, int numOfColumns) {
        String[][] data = new String[numOfGoals][numOfColumns];
        for (int goalIndex = 0; goalIndex < numOfGoals; goalIndex++) {
            data[goalIndex][INDEX_INDEX] = String.valueOf(goalIndex + 1);
            data[goalIndex][GOAL_NAME_INDEX] = goals.get(goalIndex).getGoalName();
            data[goalIndex][GOAL_TYPE_INDEX] = goals.get(goalIndex).getGoalTypeStr();
            data[goalIndex][GOAL_START_DATE_INDEX] = goals.get(goalIndex).getPrintableStartDate();
            data[goalIndex][GOAL_END_DATE_INDEX] = goals.get(goalIndex).getPrintableEndDate();
            data[goalIndex][GOAL_HABIT_NUM_INDEX] = String.valueOf(goals.get(goalIndex).getHabitListSize());
            data[goalIndex][GOAL_COMPLETION_RATE_INDEX] = goals.get(goalIndex).computeAverageCompletionRate();
        }
        return data;
    }

    /**
     * Populate a 2D array with habit data.
     *
     * @param habits       Arraylist containing all the habits.
     * @param numOfHabits  Number of habits.
     * @param numOfColumns Number of data columns.
     * @return 2D string array containing habit data.
     */
    private String[][] populateHabitData(ArrayList<Habit> habits, int numOfHabits, int numOfColumns) {
        String[][] data = new String[numOfHabits][numOfColumns];
        for (int habitIndex = 0; habitIndex < numOfHabits; habitIndex++) {
            data[habitIndex][INDEX_INDEX] = String.valueOf(habitIndex + 1);
            data[habitIndex][HABIT_NAME_INDEX] = habits.get(habitIndex).getHabitName();
            data[habitIndex][HABIT_INTERVAL_INDEX] = String.valueOf(habits.get(habitIndex).getIntervalLength());
            double[] habitStatistics = habits.get(habitIndex).getListStatistics();
            data[habitIndex][HABIT_COMPLETION_RATE_INDEX] =
                    String.format("%.2f", habitStatistics[COMPLETION_RATE_INDEX]) + PERCENT_SYMBOL;
            data[habitIndex][HABIT_COMPLETED_INDEX] = String.valueOf((int)(habitStatistics[COMPLETED_INDEX]));
            data[habitIndex][HABIT_REMAINING_INDEX] = String.valueOf((int)(habitStatistics[REMAINING_INDEX]));
            data[habitIndex][HABIT_EXPIRED_INDEX] = String.valueOf((int)(habitStatistics[EXPIRED_INDEX]));
            data[habitIndex][HABIT_STREAK_INDEX] = String.valueOf(habits.get(habitIndex).getStreak());
        }
        return data;
    }

    /**
     * Populate a 2D array with command data.
     *
     * @return 2D string array containing command data.
     */
    private String[][] populateCommandData() {
        String[][] data = new String[12][2];
        data[0][COMMAND_INDEX] = COMMAND_HELP;
        data[1][COMMAND_INDEX] = COMMAND_ADD_GOAL;
        data[2][COMMAND_INDEX] = COMMAND_ADD_HABIT;
        data[3][COMMAND_INDEX] = COMMAND_UPDATE_GOAL;
        data[4][COMMAND_INDEX] = COMMAND_UPDATE_HABIT;
        data[5][COMMAND_INDEX] = COMMAND_LIST_GOAL;
        data[6][COMMAND_INDEX] = COMMAND_LIST_HABIT;
        data[7][COMMAND_INDEX] = COMMAND_DELETE_GOAL;
        data[8][COMMAND_INDEX] = COMMAND_DELETE_HABIT;
        data[9][COMMAND_INDEX] = COMMAND_DONE_HABIT;
        data[10][COMMAND_INDEX] = COMMAND_RETURN;
        data[11][COMMAND_INDEX] = COMMAND_EXIT;
        data[0][INSTR_INDEX] = INSTR_HELP;
        data[1][INSTR_INDEX] = INSTR_ADD_GOAL;
        data[2][INSTR_INDEX] = INSTR_ADD_HABIT;
        data[3][INSTR_INDEX] = INSTR_UPDATE_GOAL;
        data[4][INSTR_INDEX] = INSTR_UPDATE_HABIT;
        data[5][INSTR_INDEX] = INSTR_LIST_GOAL;
        data[6][INSTR_INDEX] = INSTR_LIST_HABIT;
        data[7][INSTR_INDEX] = INSTR_DELETE_GOAL;
        data[8][INSTR_INDEX] = INSTR_DELETE_HABIT;
        data[9][INSTR_INDEX] = INSTR_DONE_HABIT;
        data[10][INSTR_INDEX] = INSTR_RETURN;
        data[11][INSTR_INDEX] = INSTR_EXIT;
        return data;
    }

}
