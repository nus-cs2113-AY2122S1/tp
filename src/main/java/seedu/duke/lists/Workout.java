package seedu.duke.lists;

import seedu.duke.exception.GetJackDException;

import seedu.duke.storage.models.WorkoutListModel;
import seedu.duke.storage.models.WorkoutModel;
import seedu.duke.exercises.Exercise;

import java.util.ArrayList;

/**
 * A workout contains a list of exercises in the form of ArrayList of Exercise objects.
 * Adding, deleting and other operations related to all exercises in the workout will be executed from here.
 */
public class Workout {
    private String workoutName;
    private ArrayList<Exercise> exercises;

    /**
     * Default Constructor.
     *
     * @param workoutName name that the user gives to the workout routine.
     */
    public Workout(String workoutName) {
        assert !workoutName.isEmpty();
        exercises = new ArrayList<>();
        this.workoutName = workoutName;
    }


    /**
     * To get the name of the Workout.
     *
     * @return name of the workout routine.
     */
    public String getWorkoutName() {
        return workoutName;
    }

    /**
     * Adds an exercise to the workout routine.
     *
     * @param exercise exercise that is to be added.
     */
    public void addExercise(Exercise exercise) {
        assert exercise != null;
        exercises.add(exercise);
    }

    /**
     * removes a specific exercise.
     *
     * @param displayIndex 1-based index (as seen by user) of the exercise to be removed.
     * @return exercise that was removed.
     * @throws GetJackDException when the index is greater than number of exercises or less than 0.
     */
    public Exercise removeExercise(int displayIndex) throws GetJackDException {
        if (displayIndex <= 0 || displayIndex > exercises.size()) {
            throw new GetJackDException("Invalid Exercise Index");
        }
        return exercises.remove(displayIndex - 1);
    }

    /**
     * To get all Exercises in the workout routine.
     *
     * @return all the exercises stored in the ArrayList.
     */
    public ArrayList<Exercise> getAllExercises() {
        return exercises;
    }

    /**
     * returns a specific exercise.
     *
     * @param displayIndex 1-based index (as seen by user) of the exercise to be returned.
     * @return exercise that was requested for.
     * @throws GetJackDException when the index is greater than number of exercises or less than 0.
     */
    public Exercise getExercise(int displayIndex) throws GetJackDException {
        if (displayIndex <= 0 || displayIndex > exercises.size()) {
            throw new GetJackDException("Invalid Exercise Index");
        }
        return exercises.get(displayIndex - 1);
    }

    /**
     * Converts the object to a String.
     *
     * @return name of workout routine.
     */
    @Override
    public String toString() {
        return getWorkoutName();
    }

    /**
     * Loops through all the exercise and constructs corresponding ExerciseModel objects, adding together
     * to form a WorkoutModel object to be added to the WorkoutListModel Object.
     * This is done as WorkoutModel objects are easily convertable to JSONStrings for storage.
     */
    public void convertToWorkoutStorageModel() {
        WorkoutModel workoutModel = new WorkoutModel(workoutName);
        for (Exercise exercise : exercises) {
            exercise.convertToExerciseStorageModel(workoutModel);
        }
        WorkoutListModel.addToWorkoutListModel(workoutModel);
    }
}
