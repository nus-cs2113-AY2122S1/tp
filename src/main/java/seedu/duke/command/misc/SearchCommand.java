package seedu.duke.command.misc;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.exception.GetJackDException;
import seedu.duke.exercises.Exercise;
import seedu.duke.lists.Workout;
import seedu.duke.lists.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
    public static final String MESSAGE_MATCHING_WORKOUTS = "Matching workouts: ";
    public static final String MESSAGE_NO_MATCHES_FOUND = "No matches found. ";
    public static final String MESSAGE_MATCHING_EXERCISES_IN_WORKOUT = "Matching exercises in %d) %s";

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
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) throws GetJackDException {
        Map<String, ArrayList> map = new HashMap<>();
        ArrayList<Workout> workoutList = workouts.getAllWorkouts();

        boolean matchingWorkoutsFound = getMatchingWorkouts(map, workoutList);
        boolean matchingExercisesFound = getMatchingExercises(map, workoutList);

        if (!matchingExercisesFound && !matchingWorkoutsFound) {
            return new CommandResult(MESSAGE_NO_MATCHES_FOUND);
        }

        return new CommandResult(map);
    }

    private boolean getMatchingWorkouts(Map<String, ArrayList> map, ArrayList<Workout> workoutList) {
        boolean matchesFound = false;
        ArrayList<Workout> filteredWorkouts = getFilteredWorkoutsWithWorkoutIndex(workoutList);
        if (!filteredWorkouts.isEmpty()) {
            matchesFound = true;
            map.put(MESSAGE_MATCHING_WORKOUTS, filteredWorkouts);
        }
        return matchesFound;
    }

    private boolean getMatchingExercises(Map<String, ArrayList> map, ArrayList<Workout> workoutList) {
        boolean matchingWorkouts = false;

        for (int i = 0; i < workoutList.size(); i++) {
            int displayIndex = i + 1;
            Workout w = workoutList.get(i);
            ArrayList<Exercise> exercises = w.getAllExercises();
            ArrayList<Exercise> filteredExercises = getFilteredExercisesWithExerciseIndex(exercises);
            if (!filteredExercises.isEmpty()) {
                matchingWorkouts = true;
                String matchingExerciseMessage = String.format(MESSAGE_MATCHING_EXERCISES_IN_WORKOUT,
                        displayIndex, w.getWorkoutName());
                map.put(matchingExerciseMessage, filteredExercises);
            }
        }
        return matchingWorkouts;
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
