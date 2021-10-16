package seedu.duke.ui;

import seedu.duke.exception.GetJackDException;

import java.util.ArrayList;

public class Ui {
    private static final String NEW_LINE = "\n";
    private static final String DIVIDER = "________________________________________________________";

    public static void printLineSeparator() {
        System.out.println(DIVIDER);
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

        printLineSeparator();
    }

    public static void printByeMessage() {
        printLineSeparator();
        System.out.println("Bye. Hope you get your desired body soon, have a great day!");
        printLineSeparator();

    }

    public static void printHelpMessage() {
        printLineSeparator();
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
        printLineSeparator();
    }

    /**
     * Prints specific error message based on the type of exception.
     *
     * @param e is the exception whose message we want to print
     */
    public static void printErrorMessage(GetJackDException e) {
        printLineSeparator();
        System.out.println(e.getMessage());
        printLineSeparator();
    }


    /**
     * Prints out all the items in a list.
     *
     * @param itemList is the list of items
     */
    public <T> void showItemListToUser(String displayMessage, ArrayList<T> itemList, boolean bottomLineOnly) {
        assert (!itemList.isEmpty());
        if (!bottomLineOnly) {
            Ui.printLineSeparator();
        }
        System.out.println(displayMessage);
        if (!itemList.isEmpty()) {
            printList(itemList);
        }

        Ui.printLineSeparator();
    }

    private <T> void printList(ArrayList<T> itemList) {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i) != null) {
                System.out.println((i + 1) + ". " + itemList.get(i));
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
        System.out.println(message.replace("\n", NEW_LINE));
        printLineSeparator();
    }
}
