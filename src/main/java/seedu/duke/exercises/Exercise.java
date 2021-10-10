package seedu.duke.exercises;

import seedu.duke.storage.models.ExerciseModel;
import seedu.duke.storage.models.WorkoutModel;

public class Exercise {
    protected String description;
    //protected String muscle;
    protected int sets;
    protected int reps;
    protected Boolean isDone = false;

    public Exercise(String description, int sets, int reps) {
        this.description = description;
        this.sets = sets;
        this.reps = reps;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }
    
    public Boolean getIsDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public String getStatusSymbol() {
        return ("[" + (isDone ? "X" : " ") + "] ");
    }

    @Override
    public String toString() {
        return (getStatusSymbol() + description + " | " + sets + " sets of " + reps + " reps");
    }

    public void convertToExerciseStorageModel(WorkoutModel workoutModel) {
        String setsInString = String.valueOf(sets);
        String repsInString = String.valueOf(reps);
        String doneStatus = isDone ? "true" : "false";
        workoutModel.addToWorkoutModel(new ExerciseModel(description, setsInString, repsInString, doneStatus));
    }
}
