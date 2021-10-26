package seedu.duke.storage.models;

import com.fasterxml.jackson.annotation.JsonProperty;

//@@author jonathanmui4

public class ExerciseModel {
    private String description;
    private String sets;
    private String reps;
    private String isDone;

    public ExerciseModel(@JsonProperty("description") String description,
                         @JsonProperty("sets") String sets,
                         @JsonProperty("reps") String reps,
                         @JsonProperty("isDone") String isDone) {
        this.description = description;
        this.sets = sets;
        this.reps = reps;
        this.isDone = isDone;
    }

    public String getDescription() {
        return description;
    }

    public String getSets() {
        return sets;
    }

    public String getReps() {
        return reps;
    }

    public String getIsDone() {
        return isDone;
    }
}
