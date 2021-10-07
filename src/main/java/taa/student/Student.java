package taa.student;

public class Student {
    private static final int NUM_LESSONS = 13;

    private String name;
    private String studentID;
    private final boolean[] attendance = new boolean[NUM_LESSONS];

    public Student(String name, String studentID) {
        this.name = name;
        this.studentID = studentID;
    }

    public void markAttendance(int lesson_index) {
        attendance[lesson_index] = true;
    }

    public boolean getAttendance(int lesson_index) {
        return attendance[lesson_index];
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentID() {
        return this.studentID;
    }

    @Override
    public String toString() {
        return name + ", " + studentID;
    }
}
