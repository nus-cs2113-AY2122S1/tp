package seedu.duke.model.lesson;

import seedu.duke.DukeException;
import seedu.duke.commons.core.Messages;

public class Lesson {
    private final String title;
    private final String dayOfTheWeek;
    private final String startTime;
    private final String endTime;

    public Lesson(String title, String dayOfTheWeek, String startTime, String endTime) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    /**
     * Serializes the lesson data into the correct format for storage file.
     *
     * @return serialized lesson data
     */
    public String serialize() {
        return "L" + " | " + title + " | " + dayOfTheWeek + " | " + startTime + " | " + endTime;
    }

    /**
     * Deserializes the lesson data from the storage file.
     *
     * @param data a line of string representing the serialized data
     * @return deserialized lesson data
     * @throws DukeException if the data is in invalid format
     */
    public static Lesson deserialize(String data) throws DukeException {
        try {
            String[] item = data.split(" \\| ");
            return new Lesson(item[1], item[2], item[3], item[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(Messages.ERROR_DESERIALIZING_DATA);
        }
    }

    @Override
    public String toString() {
        return "[L] " + "Title: " + title + " (Day: " + dayOfTheWeek + ") "
                + "(Start: " + startTime + ", End: " + endTime + ")";
    }
}
