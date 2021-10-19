package seedu.duke.storage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class WorkoutModel {
    private ArrayList<ExerciseModel> exercises = new ArrayList<>();
    private String workoutName;
    private String deadline;

    public WorkoutModel(String workoutName, String deadline) {
        this.workoutName = workoutName;
        this.deadline = deadline;
    }

    public WorkoutModel(@JsonProperty("exercises") ArrayList<ExerciseModel> exercises,
                        @JsonProperty("workoutName") String workoutName,
                        @JsonProperty("deadline") String deadline) {
        this.exercises = exercises;
        this.workoutName = workoutName;
        this.deadline = deadline;
    }

    public ArrayList<ExerciseModel> getExercises() {
        return exercises;
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public String getDeadline() {
        return deadline;
    }

    public void addToWorkoutModel(ExerciseModel exercise) {
        exercises.add(exercise);
    }

}
