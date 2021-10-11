package seedu.duke.storage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class WorkoutListModel {
    private static ArrayList<WorkoutModel> workouts = new ArrayList<>();

    public WorkoutListModel() {
    }

    public WorkoutListModel(@JsonProperty("workouts") ArrayList<WorkoutModel> workouts) {
        WorkoutListModel.workouts = workouts;
    }

    public ArrayList<WorkoutModel> getWorkouts() {
        return workouts;
    }

    public static void addToWorkoutListModel(WorkoutModel workout) {
        workouts.add(workout);
    }

    public static void clearWorkoutListModel() {
        workouts.clear();
    }
}
