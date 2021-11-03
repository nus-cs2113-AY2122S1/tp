package seedu.duke.command.misc;

import seedu.duke.command.Command;
import seedu.duke.command.CommandResult;
import seedu.duke.data.DeadlineWorkout;
import seedu.duke.data.Exercise;
import seedu.duke.data.Workout;
import seedu.duke.data.WorkoutList;
import seedu.duke.storage.Storage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

//@@author qqkoh

/**
 * Finds all relevant workouts and exercises that contain a particular substring in the workout name
 * or exercise description.
 */
public class SearchCommand extends Command {
    public static final String COMMAND_WORD = "search";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays workouts or exercises that contain the "
            + "specified keyword with the workout or exercise index the keyword.\n"
            + "Format: search [keyword]\n"
            + "\tParameters: KEYWORD\n"
            + "\tExample: " + COMMAND_WORD + " abs"
            + "\tExample: " + COMMAND_WORD + " 5 Nov 2021"
            + "\tIMPORTANT: The date must match the format d MMM yyyy exactly if you want to search by date.\n";


    public static final String MESSAGE_MATCHING_WORKOUTS = "Matching workouts: ";
    public static final String MESSAGE_NO_MATCHES_FOUND = "No matches found. ";
    public static final String MESSAGE_MATCHING_EXERCISES_IN_WORKOUT = "Matching exercises in (%d) %s";

    private final String filterString;

    /**
     * Constructor that sets the filter string.
     *
     * @param filterString the substring that the user wants to find within the task description.
     */
    public SearchCommand(String filterString) {
        this.filterString = filterString.toLowerCase(Locale.ROOT);
    }

    /**
     * Filters through an exercise list to find exercises that contain a keyword in the exercise description.
     * Index of exercises are preserved in the list.
     *
     * @param workout list of exercises that we want to filter through
     * @return list of filtered exercises
     */
    private static ArrayList<Exercise> filterExercisesByString(ArrayList<Exercise> workout, String filterString) {
        ArrayList<Exercise> filteredList = new ArrayList<>();
        for (Exercise e : workout) {
            if (e.getDescription().toLowerCase(Locale.ROOT).contains(filterString)) {
                filteredList.add(e);
            } else {
                filteredList.add(null);
            }
        }
        return filteredList;
    }

    /**
     * Filters through a workout list to find workouts that contain a keyword in the workout name.
     * If the user wants to search by date, the date must match exactly, in the format dd mmm yyyy.
     * Index of workouts are preserved in the list.
     *
     * @param workoutList list of workouts that we want to filter through
     * @return list of filtered exercises
     */
    private static ArrayList<Workout> filterWorkoutsByString(ArrayList<Workout> workoutList, String filterString) {
        ArrayList<Workout> filteredList = new ArrayList<>();
        for (Workout w : workoutList) {
            if (w.getWorkoutName().toLowerCase(Locale.ROOT).contains(filterString)) {
                filteredList.add(w);
            } else if (w instanceof DeadlineWorkout
                    && ((DeadlineWorkout) w).getDeadlineString().equalsIgnoreCase(filterString)) {
                filteredList.add(w);
            } else {
                filteredList.add(null);
            }
        }
        return filteredList;
    }

    /**
     * Executes search command to display all the exercises and workouts with the matching keyword provided by the user.
     *
     * @param workouts is the list of Workouts
     * @param storage  is a storage object
     * @return all the information to be displayed to the user
     */
    @Override
    public CommandResult executeUserCommand(WorkoutList workouts, Storage storage) {
        Map<String, ArrayList<?>> map = new HashMap<>();
        ArrayList<Workout> workoutList = workouts.getAllWorkouts();

        boolean matchingWorkoutsFound = addMatchingWorkouts(map, workoutList);
        boolean matchingExercisesFound = addMatchingExercises(map, workoutList);

        if (!matchingExercisesFound && !matchingWorkoutsFound) {
            return new CommandResult(MESSAGE_NO_MATCHES_FOUND);
        }

        return new CommandResult(map, false);
    }

    /**
     * Adds matching workouts to the map and returns true if matching workouts are found.
     *
     * @param map         Map whose key is the workout name and value is the workout list
     * @param workoutList list of all workouts that we want to search through
     * @return true if matching workouts were found and added to the map, false otherwise
     */
    private boolean addMatchingWorkouts(Map<String, ArrayList<?>> map, ArrayList<Workout> workoutList) {
        boolean matchesFound = false;
        ArrayList<Workout> filteredWorkouts = getFilteredWorkoutsWithWorkoutIndex(workoutList);
        if (!filteredWorkouts.isEmpty()) {
            matchesFound = true;
            map.put(MESSAGE_MATCHING_WORKOUTS, filteredWorkouts);
        }
        return matchesFound;
    }

    /**
     * Adds matching exercises to the map and returns true if matching exercises are found.
     *
     * @param map         Map whose key is the workout name and value is the list of exercises that contain the
     *                    particular keyword in that specific workout
     * @param workoutList list of all workouts that we want to filter through
     * @return true if matching exercises were found and added to the map, false otherwise
     */
    private boolean addMatchingExercises(Map<String, ArrayList<?>> map, ArrayList<Workout> workoutList) {
        boolean matchesFound = false;

        for (int i = 0; i < workoutList.size(); i++) {
            int displayIndex = i + 1;
            Workout w = workoutList.get(i);
            ArrayList<Exercise> exercises = w.getAllExercises();
            ArrayList<Exercise> filteredExercises = getFilteredExercisesWithExerciseIndex(exercises);
            if (!filteredExercises.isEmpty()) {
                matchesFound = true;
                String matchingExerciseMessage = String.format(MESSAGE_MATCHING_EXERCISES_IN_WORKOUT,
                        displayIndex, w);
                map.put(matchingExerciseMessage, filteredExercises);
            }
        }
        return matchesFound;
    }

    /**
     * Filters through a workout list to find workouts that contain a keyword in the workout name.
     * Index of workouts are preserved in the list.
     *
     * @param workoutList list of all workouts that we want to filter through
     * @return list of filtered workouts
     */
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

    /**
     * Filters through an exercise list to find exercises that contain a keyword in the exercise description.
     * Index of exercises are preserved in the list.
     *
     * @param exercises list of exercises that we want to filter through
     * @return list of filtered exercises
     */
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
}
