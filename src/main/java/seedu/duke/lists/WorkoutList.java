package seedu.duke.lists;

import seedu.duke.exception.GetJackDException;

import seedu.duke.tasks.Workout;

import java.util.ArrayList;

/**
 * Adding, deleting and other operations related to work out workouts will be executed from here.
 */
public class WorkoutList {
    private ArrayList<Workout> workouts;

    public WorkoutList() {
        workouts = new ArrayList<>();
    }

    public WorkoutList(ArrayList<Workout> loadedWorkouts) {
        workouts = loadedWorkouts;
    }

    public void addWorkout(Workout workout) {
        workouts.add(workout);
    }

    public Workout removeWorkout(int displayIndex) throws GetJackDException {
        if (displayIndex <= 0 || displayIndex > workouts.size()) {
            throw new GetJackDException("Invalid Workout Index");
        }
        return workouts.remove(displayIndex - 1);
    }

    public ArrayList<Workout> getAllWorkouts() {
        return workouts;
    }

    public Workout getWorkout(int displayIndex) throws GetJackDException {
        if (displayIndex <= 0 || displayIndex > workouts.size()) {
            throw new GetJackDException("Invalid Workout Index");
        }
        return workouts.get(displayIndex - 1);
    }
}
