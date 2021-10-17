package seedu.duke.model.task;

import seedu.duke.commons.core.Messages;
import seedu.duke.model.task.exceptions.DeserializeTaskException;
import seedu.duke.ui.Ui;

import static seedu.duke.commons.core.DayOfTheWeek.is;
import static seedu.duke.commons.core.DayOfTheWeek.toProper;

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
        return (isDone ? "1" : "0") + " | " + title + " | " + dayOfTheWeek + " | " + information;
    }

    /**
     * Deserializes the task data from the storage file.
     *
     * @param line a line of string representing the serialized data
     * @return deserialized task data
     */
    public static Task deserialize(Ui ui, String line) {
        try {
            String[] params = line.split("\\s*[|]\\s*");

            String title = params[1];

            String dayOfTheWeek = params[2];
            if (!is(dayOfTheWeek)) {
                throw new DeserializeTaskException(Messages.ERROR_DESERIALIZING_LESSON);
            }
            dayOfTheWeek = toProper(dayOfTheWeek);

            String information = params[3];

            Task task = new Task(title, dayOfTheWeek, information);

            boolean isTaskDone = params[0].equals("1");
            if (isTaskDone) {
                task.setDone();
            }

            return task;
        } catch (ArrayIndexOutOfBoundsException | DeserializeTaskException e) {
            // Ignoring the particular line
            ui.printMessage(Messages.ERROR_DESERIALIZING_TASK);
            return null;
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
