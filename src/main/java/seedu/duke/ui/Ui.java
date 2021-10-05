package seedu.duke.ui;

import seedu.duke.exception.GetJackDException;
import seedu.duke.lists.Workout;
import seedu.duke.tasks.Exercise;

import java.util.ArrayList;

public class Ui {
    public static final int DISPLAYED_INDEX_OFFSET = 1;
    private static final String INDENT = "\t";
    private static final String NEW_LINE = "\n\t";
    private static final String DIVIDER = "_______________________________";

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
                + "Enter your command below");
        printLineSeparator();
    }

    public static void printByeMessage() {
        printLineSeparator();
        System.out.println("Bye. Hope you get your desired body soon, have a great day!");
        printLineSeparator();

    }

    public static void printErrorMessage(GetJackDException e) {
        printLineSeparator();
        System.out.println(e.getMessage());
        printLineSeparator();
    }


    public void showExercisesToUser(ArrayList<Exercise> exercises) {
        showToUser(getIndexedListViewOfExercises(exercises));
    }

    public void showWorkoutsToUser(ArrayList<Workout> workouts) {
        showToUser(getIndexedListViewOfWorkouts(workouts));
    }

    private void showToUser(String... message) {
        for (String m : message) {
            System.out.println(INDENT + m.replace("\n", NEW_LINE));
        }
    }

    private String[] getIndexedListViewOfExercises(ArrayList<Exercise> relevantExercises) {
        final ArrayList<String> exercisesStringList = new ArrayList<>();
        int displayIndex = DISPLAYED_INDEX_OFFSET;
        for (Exercise exercise : relevantExercises) {
            exercisesStringList.add(displayIndex + ". " + exercise + "\n");
            displayIndex++;
        }
        return exercisesStringList.toArray(new String[0]);
    }

    private String[] getIndexedListViewOfWorkouts(ArrayList<Workout> relevantWorkouts) {
        final ArrayList<String> workoutsStringList = new ArrayList<>();
        int displayIndex = DISPLAYED_INDEX_OFFSET;
        for (Workout workout : relevantWorkouts) {
            workoutsStringList.add(displayIndex + ". " + workout + "\n");
            displayIndex++;
        }
        return workoutsStringList.toArray(new String[0]);
    }
}
