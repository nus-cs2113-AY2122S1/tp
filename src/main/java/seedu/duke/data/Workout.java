package seedu.duke.data;

import seedu.duke.exception.GetJackDException;
import seedu.duke.storage.models.WorkoutListModel;
import seedu.duke.storage.models.WorkoutModel;

import java.util.ArrayList;

import static seedu.duke.data.WorkoutList.LOGGER;

//@@author IshaaanVyas

/**
 * A workout contains a list of exercises in the form of ArrayList of Exercise objects.
 * It also contains a deadline set by the user.
 * Adding, deleting and other operations related to all exercises in the workout will be executed from here.
 */
public class Workout {
    protected final String workoutName;
    protected final ArrayList<Exercise> exercises;

    /**
     * Default Constructor.
     *
     * @param workoutName is the name that the user gives to the workout routine
     */
    public Workout(String workoutName) {
        assert !workoutName.isEmpty();
        exercises = new ArrayList<>();
        this.workoutName = workoutName;
    }

    /**
     * Constructor that takes in an Arraylist of exercises.
     *
     * @param workoutName is the name that the user gives to the workout routine
     * @param exercises   is the list of exercises that are to be pre-loaded into the workout
     */
    public Workout(String workoutName, ArrayList<Exercise> exercises) {
        assert !workoutName.isEmpty();
        assert !exercises.isEmpty();
        this.workoutName = workoutName;
        this.exercises = exercises;
    }

    /**
     * Converts the object to a String.
     *
     * @return name of workout routine
     */
    @Override
    public String toString() {
        return getWorkoutName();
    }

    /**
     * To get the name of the Workout.
     *
     * @return name of the workout routine
     */
    public String getWorkoutName() {
        return workoutName;
    }

    /**
     * Adds an exercise to the workout routine.
     *
     * @param exercise is the exercise that is to be added
     */
    public void addExercise(Exercise exercise) {
        assert exercise != null;
        LOGGER.info("Adding Exercise: " + exercise.getDescription());
        exercises.add(exercise);
    }

    /**
     * Removes a specific exercise.
     *
     * @param displayIndex is the 1-based index (as seen by user) of the exercise to be removed
     * @return exercise that was removed
     * @throws GetJackDException when the index is greater than number of exercises or less than 0
     */
    public Exercise removeExercise(int displayIndex) throws GetJackDException {
        if (displayIndex <= 0 || displayIndex > exercises.size()) {
            throw new GetJackDException("Invalid Exercise Index");
        }

        LOGGER.info("Removing Exercise");

        return exercises.remove(displayIndex - 1);
    }

    /**
     * To get all Exercises in the workout routine.
     *
     * @return all the exercises stored in the ArrayList
     */
    public ArrayList<Exercise> getAllExercises() {
        return exercises;
    }

    /**
     * Returns a specific exercise.
     *
     * @param displayIndex is the 1-based index (as seen by user) of the exercise to be returned
     * @return exercise that was requested for
     * @throws GetJackDException when the index is greater than number of exercises or less than 0
     */
    public Exercise getExercise(int displayIndex) throws GetJackDException {
        if (displayIndex <= 0 || displayIndex > exercises.size()) {
            throw new GetJackDException("Invalid Exercise Index");
        }

        return exercises.get(displayIndex - 1);
    }

    /**
     * Loops through all the exercise and constructs corresponding ExerciseModel objects, adding together
     * to form a WorkoutModel object to be added to the WorkoutListModel Object.
     * This is done as WorkoutModel objects are easily convertible to JSONStrings for storage.
     */
    public void convertToWorkoutStorageModel() {
        LOGGER.info("Generating WorkoutModel");
        WorkoutModel workoutModel = new WorkoutModel(workoutName, "");

        for (Exercise exercise : exercises) {
            exercise.convertToExerciseStorageModel(workoutModel);
        }

        WorkoutListModel.addToWorkoutListModel(workoutModel);
    }

    public void removeAllExercise() {
        exercises.clear();
    }
}
