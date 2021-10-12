package taa.attendance;

public class Attendance {
    private static final String MESSAGE_FORMAT_ATTENDANCE = "Lesson %d (%s)";

    private final int lessonNumber;
    private boolean isPresent;

    public Attendance(int lessonNumber, boolean isPresent) {
        this.lessonNumber = lessonNumber;
        this.isPresent = isPresent;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public int getLessonNumber() {
        return lessonNumber;
    }

    @Override
    public String toString() {
        String presentString = (isPresent) ? "Present" : "Absent";
        return String.format(MESSAGE_FORMAT_ATTENDANCE, lessonNumber, presentString);
    }
}
