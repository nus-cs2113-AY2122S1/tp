package seedu.duke.storage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

//@@author jonathanmui4

public class WorkoutModel {
    private ArrayList<ExerciseModel> exercises = new ArrayList<>();
    private final String workoutName;
    private final String deadline;

    public WorkoutModel(String workoutName, String deadline) {
        this.workoutName = workoutName;
        this.deadline = deadline;
    }

    /**
     * Constructor used at runtime to change Json object to a workout model.
     *
     * @param exercises   is the list of exercises
     * @param workoutName is the name of the workout
     * @param deadline    is the deadline of the workout
     */
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
