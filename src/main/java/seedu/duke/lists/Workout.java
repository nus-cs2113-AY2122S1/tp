package seedu.duke.lists;

import seedu.duke.exception.GetJackDException;
import seedu.duke.exercises.Exercise;

import java.util.ArrayList;

/**
 * Adding, deleting and other operations related to the exercises will be executed from here.
 */
public class Workout {
    private String workoutName;

    public String getWorkoutName() {
        return workoutName;
    }

    private ArrayList<Exercise> exercises;

    public Workout(String workoutName) {
        exercises = new ArrayList<>();
        this.workoutName = workoutName;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public Exercise removeExercise(int displayIndex) throws GetJackDException {
        if (displayIndex <= 0 || displayIndex > exercises.size()) {
            throw new GetJackDException("Invalid Exercise Index");
        }
        return exercises.remove(displayIndex - 1);
    }

    public ArrayList<Exercise> getAllExercises() {
        return exercises;
    }

    public Exercise getExercise(int displayIndex) throws GetJackDException {
        if (displayIndex <= 0 || displayIndex > exercises.size()) {
            throw new GetJackDException("Invalid Exercise Index");
        }
        return exercises.get(displayIndex - 1);
    }
}
