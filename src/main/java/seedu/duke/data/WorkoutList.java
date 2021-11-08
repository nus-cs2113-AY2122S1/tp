package seedu.duke.data;

import seedu.duke.exception.GetJackDException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import static seedu.duke.logger.LoggerUtil.setupLogger;

//@@author IshaaanVyas

/**
 * Represents a list of all workouts in the form of a ArrayList of Workout Objects (All workouts stored by user).
 * Adding, deleting and other operations related to workouts will be executed from here.
 */
public class WorkoutList {
    private ArrayList<Workout> workouts;
    static final Logger LOGGER = Logger.getLogger(WorkoutList.class.getName());

    public WorkoutList() {
        workouts = new ArrayList<>();
        setupLogger(LOGGER);
    }

    /**
     * Adds a workout routine to the list of workouts.
     *
     * @param workout workout routine to be added
     */
    public void addWorkout(Workout workout) {
        assert workout != null;
        LOGGER.info("Adding Workout: " + workout.getWorkoutName());
        workouts.add(workout);
    }

    /**
     * Removes a specific workout routine.
     *
     * @param displayIndex 1-based index (as seen by user) of the workout routine to be removed
     * @return workout routine that was removed
     * @throws GetJackDException when the index is greater than number of workouts or less than 0
     */
    public Workout removeWorkout(int displayIndex) throws GetJackDException {
        if (displayIndex <= 0 || displayIndex > workouts.size()) {
            throw new GetJackDException("Invalid Workout Index");
        }

        LOGGER.info("Removing Workout");

        return workouts.remove(displayIndex - 1);
    }

    /**
     * To get all workout routines stored currently.
     *
     * @return all workout routines stored in the ArrayList
     */
    public ArrayList<Workout> getAllWorkouts() {
        return workouts;
    }

    /**
     * Returns a specific workout routine.
     *
     * @param displayIndex 1-based index (as seen by user) of workout routine to be returned
     * @return workout routine that was requested for
     * @throws GetJackDException when the index is greater than number of workouts or less than 0
     */
    public Workout getWorkout(int displayIndex) throws GetJackDException {
        if (displayIndex <= 0 || displayIndex > workouts.size()) {
            throw new GetJackDException("Invalid Workout Index");
        }

        return workouts.get(displayIndex - 1);
    }

    //@@author jonathanmui4

    /**
     * Sorts Workouts according to deadlines with the workouts due earlier being top of the list. Workouts without
     * deadlines are at the bottom of the list.
     */
    public void sortWorkouts() {
        ArrayList<DeadlineWorkout> deadlineWorkouts = new ArrayList<>();
        ArrayList<Workout> normalWorkouts = new ArrayList<>();
        for (Workout workout : workouts) {
            if (workout instanceof DeadlineWorkout) {
                deadlineWorkouts.add((DeadlineWorkout) workout);
            } else {
                normalWorkouts.add(workout);
            }
        }
        ArrayList<Workout> sortedWorkouts = deadlineWorkouts.stream()
                .sorted(Comparator.comparing(DeadlineWorkout::getDeadlineDate))
                .collect(Collectors.toCollection(ArrayList::new));
        sortedWorkouts.addAll(normalWorkouts);
        workouts.clear();
        workouts.addAll(sortedWorkouts);
    }

    /**
     * Loops through all workout routines and constructs corresponding WorkoutModel objects for each,
     * to be stored in WorkoutListModel class.
     * This is done as WorkoutModel objects are easily convertible to JSONStrings for storage.
     */
    public void convertAllWorkoutsToStorageModel() {
        LOGGER.info("Generating WorkoutListModel");

        for (Workout workout : workouts) {
            workout.convertToWorkoutStorageModel();
        }
    }

    public void removeAllWorkout() {
        workouts.clear();
    }
}
