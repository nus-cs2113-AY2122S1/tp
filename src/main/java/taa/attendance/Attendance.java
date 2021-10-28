package taa.attendance;

import taa.ClassChecker;

public class Attendance implements ClassChecker {
    private static final int MIN_LESSON_NUMBER = 1;

    private static final String MESSAGE_FORMAT_ATTENDANCE = "Lesson %d (%s)";

    private final int lessonNumber;
    private boolean isPresent;

    public Attendance(int lessonNumber, boolean isPresent) {
        this.lessonNumber = lessonNumber;
        this.isPresent = isPresent;
    }

    /**
     * Gets the minimum lesson number.
     *
     * @return The minimum lesson number.
     */
    public static int getMinLessonNumber() {
        return MIN_LESSON_NUMBER;
    }

    public void setPresent(boolean present) {
        isPresent = present;
    }

    public int getLessonNumber() {
        assert lessonNumber >= MIN_LESSON_NUMBER;
        return lessonNumber;
    }

    public boolean getIsPresent() {
        return isPresent;
    }

    @Override
    public String toString() {
        String presentString = (isPresent) ? "Present" : "Absent";
        return String.format(MESSAGE_FORMAT_ATTENDANCE, lessonNumber, presentString);
    }

    /**
     * Checks if the variables in the class are valid.
     *
     * @return true if valid, else false.
     */
    @Override
    public boolean verify() {
        if (lessonNumber < MIN_LESSON_NUMBER) {
            return false;
        }

        return true;
    }
}
