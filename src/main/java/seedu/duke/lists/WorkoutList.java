package seedu.duke.lists;

import seedu.duke.exception.GetJackDException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

/**
 * Represents a list of all workouts in the form of a ArrayList of Workout Objects (All workouts stored by user).
 * Adding, deleting and other operations related to workouts will be executed from here.
 */
public class WorkoutList {
    private ArrayList<Workout> workouts;
    private final static Logger LOGGER = Logger.getLogger(WorkoutList.class.getName());

    /**
     * Default Constructor.
     */
    public WorkoutList() {
        workouts = new ArrayList<>();
        LOGGER.info("Starting worklist");
    }

    /**
     * Adds a workout routine to the list of workouts.
     *
     * @param workout workout routine to be added.
     */
    public void addWorkout(Workout workout) {
        assert workout != null;
        workouts.add(workout);
    }

    /**
     * Removes a specific workout routine.
     *
     * @param displayIndex 1-based index (as seen by user) of the workout routine to be removed.
     * @return workout routine that was removed.
     * @throws GetJackDException when the index is greater than number of workouts or less than 0.
     */
    public Workout removeWorkout(int displayIndex) throws GetJackDException {
        if (displayIndex <= 0 || displayIndex > workouts.size()) {
            throw new GetJackDException("Invalid Workout Index");
        }
        return workouts.remove(displayIndex - 1);
    }

    /**
     * To get all workout routines stored currently.
     *
     * @return all workout routines stored in the ArrayList.
     */
    public ArrayList<Workout> getAllWorkouts() {
        return workouts;
    }

    /**
     * returns a specific workout routine.
     *
     * @param displayIndex 1-based index (as seen by user) of workout routine to be returned.
     * @return workout routine that was requested for.
     * @throws GetJackDException when the index is greater than number of workouts or less than 0.
     */
    public Workout getWorkout(int displayIndex) throws GetJackDException {
        if (displayIndex <= 0 || displayIndex > workouts.size()) {
            throw new GetJackDException("Invalid Workout Index");
        }
        return workouts.get(displayIndex - 1);
    }

    /**
     * Loops through all workout routines and constructs corresponding WorkoutModel objects for each,
     * to be stored in WorkoutListModel class.
     * This is done as WorkoutModel objects are easily convertable to JSONStrings for storage.
     */
    public void convertAllWorkoutsToStorageModel() {
        for (Workout workout : workouts) {
            workout.convertToWorkoutStorageModel();
        }
    }
}
