package seedu.duke.lists;

import seedu.duke.exception.GetJackDException;

import java.util.ArrayList;

/**
 * Adding, deleting and other operations related to workouts will be executed from here.
 */
public class WorkoutList {
    private ArrayList<Workout> workouts;

    public WorkoutList() {
        workouts = new ArrayList<>();
    }

    public void addWorkout(Workout workout) {
        //assumes not null
        workouts.add(workout);
    }

    public Workout removeWorkout(int displayIndex) throws GetJackDException {
        //assume not null
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

    public void convertAllWorkoutsToStorageModel() {
        for (Workout workout : workouts) {
            workout.convertToWorkoutStorageModel();
        }
    }
}
