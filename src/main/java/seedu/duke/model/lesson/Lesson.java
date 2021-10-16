package seedu.duke.model.lesson;

import seedu.duke.commons.core.Messages;
import seedu.duke.model.lesson.exceptions.DeserializeLessonException;
import seedu.duke.ui.Ui;

import static seedu.duke.commons.core.DayOfTheWeek.is;

public class Lesson {
    private final String title;
    private final String dayOfTheWeek;
    private final String startTime;
    private final String endTime;
    private final String meetingUrl;

    public Lesson(String title, String dayOfTheWeek, String startTime, String endTime, String meetingUrl) {
        this.title = title;
        this.dayOfTheWeek = dayOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.meetingUrl = meetingUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public String getStartTime() {
        return startTime;
    }

    public String endTime() {
        return endTime;
    }

    public String getMeetingUrl() {
        return meetingUrl;
    }

    /**
     * Serializes the lesson data into the correct format for storage file.
     *
     * @return serialized lesson data
     */
    public String serialize() {
        return "L" + " | " + title + " | " + dayOfTheWeek + " | " + startTime + " | " + endTime + " | " + meetingUrl;
    }

    /**
     * Deserializes the lesson data from the storage file.
     *
     * @param data a line of string representing the serialized data
     * @return deserialized lesson data
     * @throws DeserializeLessonException if the data is in invalid format
     */
    public static Lesson deserialize(String data) throws DeserializeLessonException {
        try {
            String[] params = data.split("\\s*[|]\\s*");

            String modelType = params[0];
            if (!modelType.equals("L")) {
                throw new DeserializeLessonException(Messages.ERROR_DESERIALIZING_DATA);
            }

            String title = params[1];
            String dayOfTheWeek = params[2];
            if (!is(dayOfTheWeek)) {
                throw new DeserializeLessonException(Messages.ERROR_DESERIALIZING_DATA);
            }

            String startTime = params[3];
            String endTime = params[4];
            String meetingUrl = params[5];

            return new Lesson(title, dayOfTheWeek, startTime, endTime, meetingUrl);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DeserializeLessonException(Messages.ERROR_DESERIALIZING_DATA);
        }
    }

    @Override
    public String toString() {
        return title + System.lineSeparator()
                + Ui.PADDING + "   " + dayOfTheWeek + ", " + startTime + " - " + endTime + System.lineSeparator()
                + Ui.PADDING + "   " + "Meeting URL: " + meetingUrl;
    }
}
