package seedu.duke.tasks;

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

    public Boolean getIsDone() {
        return isDone;
    }

    public void setDone() {
        isDone = true;
    }

    public String getStatusSymbol() {
        return ("[" + (isDone ? "X" : " ") + "]");
    }

    @Override
    public String toString() {
        return (getStatusSymbol() + description + " | " + sets + " sets of " + reps + " reps");
    }
}
