package seedu.duke.ui;

import dnl.utils.text.table.TextTable;
import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.exercises.Exercise;

import java.util.ArrayList;
import java.util.Map;

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

    public static String getHelpMessage() {

        return DIVIDER + "\n"
                + "Here's a list of commands and what they do.\n"
                + "To find out more information about the command, such as input format and parameters, "
                + "enter \"help COMMAND\" where COMMAND is the command you want to know more about\n"
                + "\"add\" : Adds an exercise to a workout\n"
                + "\"done\" : Marks an exercise as done\n"
                + "\"remove\" : Removes an exercise from a workout\n"
                + "\"create\" : Creates a new workout\n"
                + "\"delete\" : Deletes a workout\n"
                + "\"list\" : Lists all your workouts\n"
                + "\"recommend\" : Recommends workouts of a given difficulty\n"
                + "\"display\" : Shows all the exercises in a specified workout\n"
                + "\"search\" : Displays workouts or exercises that contain the specified keyword\n"
                + "\"bye\" : Ends the program\n"
                + DIVIDER;
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
        String errorMessage = e.getMessage();
        assert (!errorMessage.isEmpty());

        printLineSeparator();
        printText(errorMessage);
        printLineSeparator();

        if (withIndent) {
            System.out.print(INDENT);
        }
    }

    private void printList(ArrayList itemList) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i) != null) {
                printText((i + 1) + ". " + itemList.get(i));
            }
        }
    }

    private void printMap(Map<String, ArrayList> map) {
        for (Map.Entry<String, ArrayList> m : map.entrySet()) {
            printText(m.getKey());
            printList(m.getValue());
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
     * Shows users recommend workouts based on the difficulty provided.
     *
     * @param workoutLevel is the difficulty of the workouts
     */
    public void showRecommendedWorkouts(String workoutLevel) {

        String[] exercises;

        switch (workoutLevel) {
        case "beginner":

            System.out.println("Arm");
            exercises = new String[]{"Normal Pushups", "Inclined Pushups", "Bench Dips", "Bear Crawl"};
            printRecommendTable(exercises);

            System.out.println("Abs");
            exercises = new String[]{"Situps", "Plank"};
            printRecommendTable(exercises);
            break;
        case "intermediate":
            System.out.println("Shoulders");
            exercises = new String[]{"Pike Pushups", "Supported hand stand"};
            printRecommendTable(exercises);

            System.out.println("Glutes");
            exercises = new String[]{"Kick Backs"};
            printRecommendTable(exercises);
            break;
        case "pro":
            System.out.println("Push Workout");
            exercises = new String[]{"Wide pushups"};
            printRecommendTable(exercises);

            System.out.println("Pull Workout");
            exercises = new String[]{"Pullups"};
            printRecommendTable(exercises);

            System.out.println("Leg Workout");
            exercises = new String[]{"Squats", "Lunges", "Explosive Jumps"};
            printRecommendTable(exercises);
            break;
        }
    }

    /**
     * Prints a table of recommended exercises with the given list of workouts.
     *
     * @param exercises is the pre set list of recommended exercises
     */
    private void printRecommendTable(String[] exercises) {
        //AsciiTable at = new AsciiTable();
        assert exercises != null;

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

    public void showResultToUser(CommandResult result) {
        printLineSeparator();

        if (result.feedbackToUser != null) {
            printText(result.feedbackToUser);
        }
        if (result.itemList != null) {
            printList(result.itemList);
        }
        if (result.map != null) {
            printMap(result.map);
        }

        printLineSeparator();

        if (withIndent) {
            System.out.println(INDENT);
        }
    }
}
