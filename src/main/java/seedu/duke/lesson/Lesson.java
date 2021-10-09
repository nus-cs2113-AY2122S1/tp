package seedu.duke.lesson;

import seedu.duke.exception.DukeException;

public class Lesson {
    // TODO: Implement serialization/deserialization
    private String title;
    private String dayOfTheWeek;
    private String startTime;
    private String endTime;

    /**
     * Constructor for the Lesson class.
     *
     * @param title title of the lesson
     * @param dayOfTheWeek day of the week the lesson is on
     * @param startTime starting time of the lesson
     * @param endTime ending time of the lesson
     */
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

    @Override
    public String toString() {
        return "[L] " + "Title: " + title + " (Start: " + startTime + ") " + "(End: " + endTime + ")";
    }

    /**
     * Serializes the lesson into its file data storage format.
     *
     * @return the serialized lesson
     */
    public String serialize() {
        return "L" + " | " + title + " | " + dayOfTheWeek + " | " + startTime + " | " + endTime;
    }

    /**
     * Deserializes the lesson from a line in the file data storage into a lesson object.
     *
     * @param data the line of the storage representing a lesson
     * @return the deserialized lesson object
     * @throws DukeException when the line is not deserializable into a lesson
     */
    public static Lesson deserialize(String data) throws DukeException {
        try {
            String[] item = data.split(" \\| ");
            return new Lesson(item[1], item[2], item[3], item[4]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Data storage file corrupted..");
        }
    }
}
