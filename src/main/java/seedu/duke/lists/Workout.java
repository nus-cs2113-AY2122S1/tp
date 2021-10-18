package seedu.duke.lists;

import seedu.duke.exception.GetJackDException;

import seedu.duke.storage.models.WorkoutListModel;
import seedu.duke.storage.models.WorkoutModel;
import seedu.duke.exercises.Exercise;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static seedu.duke.lists.WorkoutList.LOGGER;

/**
 * A workout contains a list of exercises in the form of ArrayList of Exercise objects.
 * It also contains a deadline set by the user.
 * Adding, deleting and other operations related to all exercises in the workout will be executed from here.
 */
public class Workout {
    private final String workoutName;
    private final ArrayList<Exercise> exercises;
    private final String deadline;
    private final LocalDate deadlineDate;

    /**
     * Default Constructor.
     *
     * @param workoutName is the name that the user gives to the workout routine
     * @param deadlineDate Workout deadline in the form of yyyy-mm-dd
     */
    public Workout(String workoutName, LocalDate deadlineDate) {
        assert !workoutName.isEmpty();
        assert deadlineDate != null;
        exercises = new ArrayList<>();
        this.workoutName = workoutName;
        this.deadlineDate = deadlineDate;
        deadline = deadlineDate.format(DateTimeFormatter.ofPattern("dd MMM yyyy"));
    }

    /**
     * Converts the object to a String.
     *
     * @return name of workout routine
     */
    @Override
    public String toString() {
        assert !deadline.isEmpty();
        return getWorkoutName() + " finish by: " + deadline;
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
        WorkoutModel workoutModel = new WorkoutModel(workoutName, deadlineDate.toString());

        for (Exercise exercise : exercises) {
            exercise.convertToExerciseStorageModel(workoutModel);
        }

        WorkoutListModel.addToWorkoutListModel(workoutModel);
    }
}
