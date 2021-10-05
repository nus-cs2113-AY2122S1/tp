package seedu.duke.lists;

import seedu.duke.exception.GetJackDException;
import seedu.duke.tasks.Exercise;

import java.util.ArrayList;

/**
 * Adding, deleting and other operations related to the exercises will be executed from here.
 */
public class Workout {
    public static ArrayList<Exercise> exercises;

    public Workout() {
        exercises = new ArrayList<>();
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
        return exercises.get(index);
    }
}
