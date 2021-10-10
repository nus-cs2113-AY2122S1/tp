package seedu.duke.ui;

import seedu.duke.command.Command;
import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.exercises.Exercise;

import java.util.ArrayList;

public class Ui {
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String INDENT = "\t";
    private static final String NEW_LINE = "\n\t";
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
        System.out.println("\"add\" : adds an exercise to a workout");
        System.out.println("\"done\" : marks an exercise as done");
        System.out.println("\"remove\" : removes an exercise from a workout");
        System.out.println("\"create\" : Creates a new workout");
        System.out.println("\"delete\" : deletes a workout");
        System.out.println("\"list\" : lists all your workouts");
        System.out.println("\"display\" : shows all the exercises in a specified workout");
        System.out.println("\"bye\" : Ends the program");
        printLineSeparator();
    }

    public static void printErrorMessage(GetJackDException e) {
        printLineSeparator();
        System.out.println(e.getMessage());
        printLineSeparator();
    }


    public void showExercisesToUser(ArrayList<Exercise> exercises) {
        Ui.printLineSeparator();
        System.out.println("Exercises in mentioned workout:");

        //Lists down all the tasks added along with its status
        for (int i = 0; i < exercises.size(); i++) {
            System.out.println((i + 1) + ". " + exercises.get(i));
        }
        Ui.printLineSeparator();
    }

    public void showWorkoutsToUser(ArrayList<Workout> workouts) {
        Ui.printLineSeparator();
        System.out.println("Workout list:");

        //Lists down all the tasks added along with its status
        for (int i = 0; i < workouts.size(); i++) {
            System.out.println((i + 1) + ". " + workouts.get(i));
        }

        Ui.printLineSeparator();
    }

    public void showToUser(String... message) {
        for (String m : message) {
            printLineSeparator();
            System.out.println(INDENT + m.replace("\n", NEW_LINE));
            printLineSeparator();
        }
    }
}
