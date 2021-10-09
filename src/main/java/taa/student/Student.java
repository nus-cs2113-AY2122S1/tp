package taa.student;

/**
 * Represents students.
 */
public class Student {
    private static final int NUM_LESSONS = 13;

    private String id;
    private String name;
    private final boolean[] attendance = new boolean[NUM_LESSONS];

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Marks the student as present for a particular lesson.
     *
     * @param lessonIndex The lesson index the student is present for
     */
    public void markAttendance(int lessonIndex) {
        attendance[lessonIndex] = true;
    }

    /**
     * Returns the attendance status of the student for a particular lesson.
     *
     * @param lessonIndex The lesson index
     * @return The attendance status of the student for the lesson
     */
    public boolean isPresent(int lessonIndex) {
        return attendance[lessonIndex];
    }

    /**
     * Sets the name of the student.
     *
     * @param name The name of the student
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of the student.
     *
     * @return The name of the student
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the student ID of the student.
     *
     * @param id the student ID of the student
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * returns the student ID of the student.
     *
     * @return the student ID of the student
     */
    public String getId() {
        return this.id;
    }

    /**
     * Overrides default toString method with the custom print message.
     *
     * @return the custom print message
     */
    @Override
    public String toString() {
        return String.format("%s - %s", id, name);
    }
}
