package seedu.duke.ui;

import de.vandermeer.asciitable.AsciiTable;
import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;

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

    public static String getHelpMessage() {

        return "Here's a list of commands and what they do.\n"
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
                + "\"bye\" : Ends the program";
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

    private void printTable(Map<String, ArrayList> map) {
        assert !map.isEmpty();


        for (Map.Entry<String, ArrayList> m : map.entrySet()) {
            AsciiTable at = new AsciiTable();
            at.addRule();
            String[] columnNames = {"Index", "Exercise name", "Sets", "Reps"};
            at.addRow((Object[]) columnNames);
            at.addRule();
            String workoutName = m.getKey();
            printText(workoutName);
            ArrayList exercises = m.getValue();
            for (int i = 0; i < exercises.size(); i++) {
                String displayIndex = String.valueOf(i + 1);
                String exerciseName = (String) exercises.get(i);
                String sets = String.valueOf(2);
                String reps = String.valueOf(8);
                at.addRow(displayIndex, exerciseName, sets, reps);
                at.addRule();
            }
            System.out.println(at.render());
        }
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
            if (result.isTable) {
                printTable(result.map);
            } else {
                printMap(result.map);
            }
        }

        printLineSeparator();

        if (withIndent) {
            System.out.println(INDENT);
        }
    }
}
