package seedu.duke.storage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

//@@author jonathanmui4

public class WorkoutListModel {
    private static ArrayList<WorkoutModel> workouts = new ArrayList<>();

    public WorkoutListModel() {
    }

    /**
     * Constructor used at runtime to change Json object to a workout model.
     *
     * @param workouts is the list of workout
     */
    public WorkoutListModel(@JsonProperty("workouts") ArrayList<WorkoutModel> workouts) {
        WorkoutListModel.workouts = workouts;
    }

    public static void addToWorkoutListModel(WorkoutModel workout) {
        workouts.add(workout);
    }

    public static void clearWorkoutListModel() {
        workouts.clear();
    }

    public ArrayList<WorkoutModel> getWorkouts() {
        return workouts;
    }
}
