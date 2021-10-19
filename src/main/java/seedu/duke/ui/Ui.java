package seedu.duke.ui;

import dnl.utils.text.table.TextTable;
import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;
import seedu.duke.exercises.Exercise;

import java.util.ArrayList;

public class Ui {
    private static final String INDENT = "\t\t\t";
    private static final String DIVIDER = "________________________________________________________";

    private final boolean withIndent;
    private final String prefix;
    private final String newLine;

    public Ui() {
        withIndent = Command.workoutMode != 0;
        if (withIndent) {
            prefix = INDENT;
            newLine = System.lineSeparator() + INDENT;
        } else {
            prefix = "";
            newLine = System.lineSeparator();
        }
    }

    public static void printWelcomeMessage() {
        String logo = "\n"
                + "   ______          _        _____              __      _  ______\n"
                + " .' ___  |        / |_     |_   _|            [  |  _ | ||_   _ `.\n"
                + "/ .'   \\_|  .---.`| |-'      | | ,--.   .---.  | | / ]\\_|  | | `. \\\n"
                + "| |   ____ / /__\\\\| |    _   | |`'_\\ : / /'`\\] | '' <      | |  | |\n"
                + "\\ `.___]  || \\__.,| |,  | |__' |// | |,| \\__.  | |`\\ \\    _| |_.' /\n"
                + " `._____.'  '.__.'\\__/  `.____.'\\'-;__/'.___.'[__|  \\_]  |______.'\n";

        System.out.println("Welcome to" + logo + "\nLet's begin the journey to achieve the physique you desire!\n"
                + "If you don't know where to start, type \"help\" for a list of possible commands.\n"
                + "Enter your command below.");

        System.out.println(DIVIDER);
    }

    public static void printByeMessage() {
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope you get your desired body soon, have a great day!");
        System.out.println(DIVIDER);

    }

    public static void printHelpMessage() {
        System.out.println(DIVIDER);
        System.out.println("Here's a list of commands and what they do.");
        System.out.println("To find out more information about the command, such as input format and parameters, "
                + "enter \"help COMMAND\" where COMMAND is the command you want to know more about");
        System.out.println("\"add\" : Adds an exercise to a workout");
        System.out.println("\"done\" : Marks an exercise as done");
        System.out.println("\"remove\" : Removes an exercise from a workout");
        System.out.println("\"create\" : Creates a new workout");
        System.out.println("\"delete\" : Deletes a workout");
        System.out.println("\"list\" : Lists all your workouts");
        System.out.println("\"recommend\" : Recommends workouts of a given difficulty");
        System.out.println("\"display\" : Shows all the exercises in a specified workout");
        System.out.println("\"search\" : Displays workouts or exercises that contain the specified keyword");
        System.out.println("\"bye\" : Ends the program");
        System.out.println(DIVIDER);
    }

    private void printText(String message) {
        System.out.println(prefix + message.replace("\n", newLine));
    }

    private void printLineSeparator() {
        printText(DIVIDER);
    }

    /**
     * Prints specific error message based on the type of exception.
     *
     * @param e is the exception whose message we want to print
     */
    public void printErrorMessage(GetJackDException e) {
        showToUser(e.getMessage());
    }


    /**
     * Prints out all the items in a list.
     *
     * @param displayMessage message to be displayed to the user
     * @param itemList       is the list of items
     * @param bottomLineOnly if true, displays bottom line only, otherwise, display top and bottom line
     */
    public <T> void showItemListToUser(String displayMessage, ArrayList<T> itemList, boolean bottomLineOnly) {
        assert (!itemList.isEmpty());
        if (!bottomLineOnly) {
            printLineSeparator();
        }
        printText(displayMessage);
        if (!itemList.isEmpty()) {
            printList(itemList);
        }
        printLineSeparator();

        if (withIndent) {
            System.out.print(INDENT);
        }
    }

    private <T> void printList(ArrayList<T> itemList) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i) != null) {
                printText((i + 1) + ". " + itemList.get(i));
            }
        }
    }

    public void printExerciseTable(String displayMessage, ArrayList<Exercise> exerciseList) {
        printLineSeparator();
        printText(displayMessage);

        String[] columnNames = {"Index", "Exercise name", "Sets", "Reps", "Done"};
        String[][] data = exerciseListTo2DArray(columnNames.length, exerciseList);
        TextTable tt = new TextTable(columnNames, data);
        tt.printTable();

        printLineSeparator();
    }

    private String[][] exerciseListTo2DArray(int numOfColumns, ArrayList<Exercise> exerciseList) {
        String[][] data = new String[exerciseList.size()][numOfColumns];
        for (int i = 0; i < exerciseList.size(); i++) {
            Exercise e = exerciseList.get(i);
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = e.getDescription();
            data[i][2] = String.valueOf(e.getSets());
            data[i][3] = String.valueOf(e.getReps());
            data[i][4] = e.getIsDone() ? "X" : " ";
        }
        return data;
    }

    /**
     * Prints the specific message needed at specific points.
     *
     * @param message is the unique message to be shown
     */
    public void showToUser(String message) {
        assert (!message.isEmpty());
        printLineSeparator();
        printText(message);
        printLineSeparator();

        if (withIndent) {
            System.out.print(INDENT);
        }
    }

    /**
     * Shows users recommend workouts based on the difficulty provided.
     *
     * @param workoutLevel is the difficulty of the workouts
     */
    public void showRecommendedWorkouts(String workoutLevel) {
        String[] exercises;

        if (workoutLevel.equals("beginner")) {

            System.out.println("Arm");
            exercises = new String[]{"Normal Pushups", "Inclined Pushups", "Bench Dips", "Bear Crawl"};
            printRecommendTable(exercises);

            System.out.println("Abs");
            exercises = new String[]{"Situps", "Plank"};
            printRecommendTable(exercises);
        } else if (workoutLevel.equals("intermediate")) {
            System.out.println("Shoulders");
            exercises = new String[]{"Pike Pushups", "Supported hand stand"};
            printRecommendTable(exercises);

            System.out.println("Glutes");
            exercises = new String[]{"Kick Backs"};
            printRecommendTable(exercises);
        } else if (workoutLevel.equals("pro")) {
            System.out.println("Push Workout");
            exercises = new String[]{"Wide pushups"};
            printRecommendTable(exercises);

            System.out.println("Pull Workout");
            exercises = new String[]{"Pullups"};
            printRecommendTable(exercises);

            System.out.println("Leg Workout");
            exercises = new String[]{"Squats", "Lunges", "Explosive Jumps"};
            printRecommendTable(exercises);
        }
    }

    /**
     * Prints a table of recommended exercises with the given list of workouts.
     *
     * @param exercises is the pre set list of recommended exercises
     */
    private void printRecommendTable(String[] exercises) {
        String[][] data;
        TextTable tt;
        String[] columnNames = {"Index", "Exercise name", "Sets", "Reps"};

        data = new String[exercises.length][columnNames.length];
        for (int i = 0; i < exercises.length; i++) {
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = exercises[i];
            data[i][2] = "2";
            data[i][3] = "8";
        }
        tt = new TextTable(columnNames, data);
        tt.printTable();

        printLineSeparator();
    }
}
