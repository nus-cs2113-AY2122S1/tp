package taa.student;

import java.util.HashMap;

/**
 * Represents students.
 */
public class Student {
    private static final int NUM_LESSONS = 13;

    private String name;
    private String studentID;
    private final boolean[] attendance = new boolean[NUM_LESSONS];

    private final HashMap<String, Double> results = new HashMap<>();

    public Student(String name, String studentID) {
        this.name = name;
        this.studentID = studentID;
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
     * @param studentID the student ID of the student
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * returns the student ID of the student.
     *
     * @return the student ID of the student
     */
    public String getStudentID() {
        return this.studentID;
    }

    /**
     * Overrides default toString method with the custom print message.
     *
     * @return the custom print message
     */
    @Override
    public String toString() {
        return name + ", " + studentID;
    }

    /**
     * Adds a key,value pair to the hashmap.
     * @param assessmentName key of the hashmap
     * @param marks value to be stored under the key given
     */
    public void setMarks(String assessmentName, double marks) {
        results.put(assessmentName, marks);
    }

    public double getMarks(String assessmentName) {
        return results.get(assessmentName);
    }

    public HashMap<String, Double> getAllMarks() {
        return results;
    }
}
