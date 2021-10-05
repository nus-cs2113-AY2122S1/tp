package seedu.duke.student;

public class Student {
    private static int TOTAL_WEEKS = 13;
    private boolean[] attendance;

    public Student() {
        this.attendance = new boolean[TOTAL_WEEKS];
    }
}
