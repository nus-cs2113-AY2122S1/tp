package seedu.duke.task;

import seedu.duke.exception.DukeException;

public class Task {
    // TODO: Implement serialization/deserialization

    private String title;
    private String dayOfTheWeek;
    private String information = "";
    private boolean isDone;

    /**
     * Constructor for the Task with the parameter "information" given.
     *
     * @param title title of the task
     * @param dayOfTheWeek day of the week the task is to be done
     * @param information extra information on the task
     */
    public Task(String title, String dayOfTheWeek, String information) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.information = information;
        this.isDone = false;
    }

    public String getTitle() {
        return title;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    /**
     * Returns the symbol representing the done status of the task.
     *
     * @return the symbol representing whether the task is done
     */
    public String getDoneSymbol() {
        return isDone ? "[X]" : "[ ]";
    }

    public String getInformation() {
        return information;
    }

    public void setDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public String toString() {
        // TODO: Improve formatting
        return "[T]" + getDoneSymbol() + " Title: " + title
                + (information.isBlank() ? "" : " (Info: " + information + ")");
    }

    /**
     * Serializes the task into its file data storage format.
     *
     * @return the serialized task
     */
    public String serialize() {
        return "T" + " | " + (isDone ? "1" : "0") + " | " + title + " | " + dayOfTheWeek + " | " + information;
    }

    /**
     * Deserializes the task from a line in the file data storage into a task object.
     *
     * @param data the line of the storage representing a task
     * @return the deserialized task object
     * @throws DukeException when the line is not deserializable into a task
     */
    public static Task deserialize(String data) throws DukeException {
        try {
            String[] item = data.split(" \\| ", -1);
            Task task = new Task(item[2], item[3], item[4]);
            boolean isTaskDone = item[1].equals("1");
            if (isTaskDone) {
                task.setDone();
            }
            return task;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Data storage file corrupted..");
        }
    }
}
