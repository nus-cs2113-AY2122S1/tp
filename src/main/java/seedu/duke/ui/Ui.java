package seedu.duke.ui;

import seedu.duke.command.Command;
import seedu.duke.command.IncorrectCommand;
import seedu.duke.command.RecommendWorkoutCommand;
import seedu.duke.exception.GetJackDException;

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
        System.out.println("\"display\" : Shows all the exercises in a specified workout");
        System.out.println("\"search\" : Displays workouts or exercises that contain the specified keyword");
        System.out.println("\"bye\" : Ends the program");
        System.out.println(DIVIDER);
    }

    private void printText(String message) {
        System.out.println(prefix + message.replace("\n", newLine));
    }

    public void printLineSeparator() {
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

    public void showRecommendedWorkouts(String workoutLevel) {
        if (workoutLevel.equals("beginner")) {

        } else if (workoutLevel.equals("intermediate")) {

        } else if (workoutLevel.equals("pro")) {

        }
    }
}
