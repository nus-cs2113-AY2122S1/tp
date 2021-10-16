package seedu.duke.command;

import seedu.duke.exception.GetJackDException;
import seedu.duke.exercises.Exercise;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;
import seedu.duke.ui.Ui;

import java.util.ArrayList;

/**
 * Finds all relevant workouts and exercises that contain a particular substring in the workout name
 * or exercise description.
 */
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays workouts or exercises that contain the "
            + "specified keyword with the workout or exercise index the keyword.\n"
            + "\tParameters: KEYWORD\n"
            + "\tExample: " + COMMAND_WORD + " abs";

    private final String filterString;

    /**
     * Constructor that sets the filter string.
     *
     * @param filterString the substring that the user wants to find within the task description.
     */
    public SearchCommand(String filterString) {
        this.filterString = filterString;
    }

    @Override
    public void executeUserCommand(WorkoutList workouts, Ui ui, Storage storage) throws GetJackDException {
        boolean matchesFound = false;
        ArrayList<Workout> workoutList = workouts.getAllWorkouts();
        ArrayList<Workout> filteredWorkouts = getFilteredWorkoutsWithWorkoutIndex(workoutList);
        if (!filteredWorkouts.isEmpty()) {
            matchesFound = true;
            ui.showItemListToUser("Matching workouts: ", filteredWorkouts, true);
        }

        for (int i = 0; i < workoutList.size(); i++) {
            int displayIndex = i + 1;
            Workout w = workoutList.get(i);
            ArrayList<Exercise> exercises = w.getAllExercises();
            ArrayList<Exercise> filteredExercises = getFilteredExercisesWithExerciseIndex(exercises);
            if (!filteredExercises.isEmpty()) {
                matchesFound = true;
                String displayMessage = String.format("Matching exercises in %d. %s", displayIndex, w.getWorkoutName());
                ui.showItemListToUser(displayMessage, filteredExercises, true);
            }
        }

        if (!matchesFound) {
            ui.showToUser("No matches found");
        }
    }

    private ArrayList<Workout> getFilteredWorkoutsWithWorkoutIndex(ArrayList<Workout> workoutList) {
        ArrayList<Workout> filteredWorkouts = filterWorkoutsByString(workoutList, filterString);
        boolean matchingWorkoutFound = false;
        for (Workout w : filteredWorkouts) {
            if (w != null) {
                matchingWorkoutFound = true;
                break;
            }
        }
        if (matchingWorkoutFound) {
            return filteredWorkouts;
        }
        return new ArrayList<>();
    }

    private ArrayList<Exercise> getFilteredExercisesWithExerciseIndex(ArrayList<Exercise> exercises) {
        boolean matchingExercisesFound = false;

        ArrayList<Exercise> filteredExercises = filterExercisesByString(exercises, filterString);
        for (Exercise e : filteredExercises) {
            if (e != null) {
                matchingExercisesFound = true;
                break;
            }
        }
        if (matchingExercisesFound) {
            return filteredExercises;
        }
        return new ArrayList<>();
    }

    private static ArrayList<Exercise> filterExercisesByString(ArrayList<Exercise> workout, String filterString) {
        ArrayList<Exercise> filteredList = new ArrayList<>();
        for (Exercise e : workout) {
            if (e.getDescription().contains(filterString)) {
                filteredList.add(e);
            } else {
                filteredList.add(null);
            }
        }
        return filteredList;
    }

    private static ArrayList<Workout> filterWorkoutsByString(ArrayList<Workout> workoutList, String filterString) {
        ArrayList<Workout> filteredList = new ArrayList<>();
        for (Workout w : workoutList) {
            if (w.getWorkoutName().contains(filterString)) {
                filteredList.add(w);
            } else {
                filteredList.add(null);
            }
        }
        return filteredList;
    }
}
