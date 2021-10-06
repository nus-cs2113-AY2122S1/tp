package seedu.duke;

public class Todo extends Task{

    public void setDone() {
        isDone = "[X]";
    }

    public String isDone() {
        return isDone;
    }

    protected String isDone;

    protected String taskType = "[T]";

    /**
     * Constructor of the Event class
     *
     * @param description the user input to describe the todo task.
     */
    public Todo(String description) {
        this.description = description.substring(5);
        isDone = "[ ]";
    }
    @Override
    public String toString() {
        return taskType + isDone + " " + description;
    }
}