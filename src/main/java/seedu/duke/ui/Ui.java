package seedu.duke.ui;

import de.vandermeer.asciitable.AsciiTable;
import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.data.Exercise;

import java.util.ArrayList;
import java.util.Map;

//@@author qqkoh

public class Ui {
    private static final String INDENT = "\t\t\t";
    private static final String DIVIDER = "________________________________________________________";

    private final String prefix;
    private final String newLine;

    /**
     * Constructor that is used to update the formatting of the result shown to the user depending on the workout mode.
     */
    public Ui() {
        boolean withIndent = Command.workoutMode != 0;
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

    /**
     * Prints a message with the required indent.
     *
     * @param message String to be printed to the user
     */
    private void printText(String message) {
        System.out.println(prefix + message.replace("\n", newLine));
    }

    /**
     * Prints a horizontal line with the required indent.
     */
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

    }

    /**
     * Prints an indexed list with the required indent.
     *
     * @param itemList list to be printed
     */
    private void printList(ArrayList<?> itemList) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i) != null) {
                printText((i + 1) + ". " + itemList.get(i));
            }
        }
    }

    /**
     * Prints a map as multiple indexed lists with their accompanying message for each list.
     *
     * @param map map containing multiple list, each with its own message
     */
    private void printMap(Map<String, ArrayList<?>> map) {
        for (Map.Entry<String, ArrayList<?>> m : map.entrySet()) {
            printText(m.getKey());
            printList(m.getValue());
        }
    }

    /**
     * Gets the exercise table.
     *
     * @param map contains multiple exercise lists
     * @return exercise table
     */
    public AsciiTable getExerciseTable(Map.Entry<String, ArrayList<?>> map) {
        AsciiTable at = new AsciiTable();
        at.addRule();
        String[] columnNames = {"Index", "Exercise name", "Sets", "Reps"};
        at.addRow((Object[]) columnNames);
        at.addRule();

        ArrayList<?> list = map.getValue();
        for (int i = 0; i < list.size(); i++) {
            Object e = list.get(i);
            if (e instanceof Exercise) {
                int displayIndex = i + 1;
                String description = ((Exercise) e).getDescription();
                int sets = ((Exercise) e).getSets();
                int reps = ((Exercise) e).getReps();
                at.addRow(displayIndex, description, sets, reps);
                at.addRule();
            }
        }
        return at;
    }

    /**
     * Prints the exercise table.
     *
     * @param map contains multiple exercise lists
     */
    public void printExerciseTable(Map<String, ArrayList<?>> map) {
        assert map != null;
        assert !map.isEmpty();

        for (Map.Entry<String, ArrayList<?>> workout : map.entrySet()) {
            String workoutName = workout.getKey();
            printText(workoutName);
            AsciiTable at = getExerciseTable(workout);
            System.out.println(at.render());
        }
    }

    /**
     * Shows result to user.
     *
     * @param result result to be shown to user
     */
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
                printExerciseTable(result.map);
            } else {
                printMap(result.map);
            }
        }

        printLineSeparator();
    }
}
