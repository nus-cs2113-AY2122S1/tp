package taa.student;

import taa.attendance.Attendance;

/**
 * Represents students.
 */
public class Student {
    private static final int NUM_LESSONS = 13;

    private String id;
    private String name;
    private final String[] individualAttendances = new String[NUM_LESSONS];
    private static final String ABSENT_MARK = "0";

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private void initIndividualAttendances(){
        for (int i = 0; i < NUM_LESSONS; i++){
            individualAttendances[i] = ABSENT_MARK;
        }
    }

    /**
     * Marks the student as present or absent for a particular lesson.
     *
     * @param attendance
     */
    public void setAttendance(Attendance attendance) {
        int lessonIndex = Integer.parseInt(attendance.getLessonIndex());
        initIndividualAttendances();
        individualAttendances[lessonIndex] = attendance.markAttendance();
    }


    /**
     * Returns the attendance status of the student for a particular lesson.
     *
     * @param lessonIndex The lesson index
     * @return The attendance status of the student for the lesson
     */
    public String isPresent(int lessonIndex) {
        return individualAttendances[lessonIndex];
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

    public String[] getIndividualAttendances() {
        return individualAttendances;
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
