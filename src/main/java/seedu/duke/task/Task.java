package seedu.duke.task;

import seedu.duke.exception.DukeException;
import seedu.duke.ui.Ui;

public class Task {
    private final String title;
    private final String dayOfTheWeek;
    private final String information;
    private boolean isDone;

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

    public String getInformation() {
        return information;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    public void setDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    /**
     * Serializes the task data into the correct format for storage file.
     *
     * @return serialized task data
     */
    public String serialize() {
        return "T" + " | " + (isDone ? "1" : "0") + " | " + title + " | " + dayOfTheWeek + " | " + information;
    }

    /**
     * Deserializes the task data from the storage file.
     *
     * @param data a line of string representing the serialized data
     * @return deserialized task data
     * @throws DukeException if the data is in invalid format
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

    @Override
    public String toString() {
        return "[T]" + getStatusIcon()
                + " Title: " + title + " (Day: " + dayOfTheWeek + ")"
                + (information.isBlank()
                        ? ""
                        : System.lineSeparator() + Ui.PADDING + "          Info: " + information);
    }
}
