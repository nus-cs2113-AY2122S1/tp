package seedu.duke.data;

import seedu.duke.storage.models.ExerciseModel;
import seedu.duke.storage.models.WorkoutModel;

import java.util.logging.Logger;

import static seedu.duke.logger.LoggerUtil.setupLogger;

//@@author JMattChiam

/**
 * Represents an exercise inside a workout.
 * It contains basic information of an exercise such as reps, sets and status.
 */
public class Exercise {
    private static final Logger LOGGER = Logger.getLogger(Exercise.class.getName());
    protected String description;
    protected int sets;
    protected int reps;
    protected Boolean isDone = false;

    public Exercise(String description, int sets, int reps) {
        this.description = description;
        this.sets = sets;
        this.reps = reps;
        setupLogger(LOGGER);
    }

    /**
     * Returns the exercise description.
     *
     * @return the exercise name
     */
    public String getDescription() {
        assert description != null;
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public int getReps() {
        return reps;
    }

    public void setReps(int reps) {
        this.reps = reps;
    }

    public Boolean getIsDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    /**
     * Marks exercise as either completed or not completed.
     *
     * @return the current status of the exercise
     */
    public String getStatusSymbol() {
        assert isDone != null;
        return ("[" + (isDone ? "X" : " ") + "] ");
    }

    /**
     * Converts the object to a String.
     *
     * @return exercise with sets and reps in specified format
     */
    @Override
    public String toString() {
        assert description != null;
        return (getStatusSymbol() + description + " | " + sets + " sets of " + reps + " reps");
    }

    /**
     * Stores the sets, reps and status values as strings and converts the current exercise
     * into an ExerciseModel that is added to the WorkoutModel.
     * This is done as WorkoutModel objects are easily convertible to JSONStrings for storage.
     *
     * @param workoutModel is the model of workout stored inside Json file
     */
    public void convertToExerciseStorageModel(WorkoutModel workoutModel) {
        String setsInString = String.valueOf(sets);
        String repsInString = String.valueOf(reps);
        String doneStatus = isDone ? "true" : "false";

        workoutModel.addToWorkoutModel(new ExerciseModel(description, setsInString, repsInString, doneStatus));

        LOGGER.info("WorkoutModel with ExerciseModel completed");
    }
}
