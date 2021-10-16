package seedu.duke.storage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class WorkoutModel {
    private ArrayList<ExerciseModel> exercises = new ArrayList<>();
    private String workoutName;

    public WorkoutModel(String workoutName) {
        this.workoutName = workoutName;
    }

    public WorkoutModel(@JsonProperty("exercises") ArrayList<ExerciseModel> exercises,
                        @JsonProperty("workoutName") String workoutName) {
        this.exercises = exercises;
        this.workoutName = workoutName;
    }

    public ArrayList<ExerciseModel> getExercises() {
        return exercises;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public void addToWorkoutModel(ExerciseModel exercise) {
        exercises.add(exercise);
    }

}
