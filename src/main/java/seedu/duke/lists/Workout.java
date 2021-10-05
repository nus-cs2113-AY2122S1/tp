package seedu.duke.lists;

import seedu.duke.exception.GetJackDException;
import seedu.duke.tasks.Exercise;

import java.util.ArrayList;


public class Workout {
    public ArrayList<Exercise> exercises;
    private final String description;

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return getDescription();
    }

    public Workout(String description) {
        exercises = new ArrayList<>();
        this.description = description;
    }

    public void addExercise(Exercise exercise) {
        exercises.add(exercise);
    }

    public Exercise removeExercise(int index) throws GetJackDException {
        if (index <= 0 || index > exercises.size()) {
            throw new GetJackDException("Invalid Exercise Index");
        }
        return exercises.remove(index - 1);
    }

    public ArrayList<Exercise> getAllExercises() {
        return exercises;
    }

    public Exercise getExercise(int index) throws GetJackDException {
        if (index <= 0 || index > exercises.size()) {
            throw new GetJackDException("Invalid Exercise Index");
        }
        return exercises.get(index - 1);
    }
}
