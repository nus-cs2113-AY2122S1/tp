package seedu.duke.lesson;

public class Lesson {
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

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    @Override
    public String toString() {
        return "[L]" + "    " + "Title: " + title + " (Start: " + startTime + ") " + "(End: " + endTime + ")";
    }
}
