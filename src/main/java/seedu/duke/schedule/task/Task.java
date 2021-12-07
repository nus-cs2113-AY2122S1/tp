package seedu.duke.schedule.task;

//@@author swatimahadevan

/**
 * Represents the Task object.
 */
public abstract class Task {
    protected String description;
    protected String date;

    /**
     * Constructor of Task class.
     *
     * @param description of task
     * @param date of task
     */
    public Task(String description, String date) {
        this.description = description;
        this.date = date;
    }

    /**
     * Get task description.
     *
     * @return task description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get date of task.
     *
     * @return date
     */
    public String getDate() {
        return date;
    }

    public abstract String toSaveFileFormat();

    /**
     * Returns task description.
     *
     * @return description of task
     */
    public String toString() {
        return description;
    }

}