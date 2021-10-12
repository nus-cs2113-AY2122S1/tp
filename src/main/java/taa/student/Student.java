package taa.student;

import taa.attendance.Attendance;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents students.
 */
public class Student {
    private static final int NUM_LESSONS = 13;

    private String id;
    private String name;
    private final String[] individualAttendances = new String[NUM_LESSONS];
    private static final String ABSENT_MARK = "0";

    private final ArrayList<Attendance> attendance;
    private final HashMap<String, Double> results;


    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.attendance = new ArrayList<>();
        this.results = new HashMap<>();
    }

    private void initIndividualAttendances(){
        for (int i = 0; i < NUM_LESSONS; i++){
            individualAttendances[i] = ABSENT_MARK;
        }
    }

    /**
     * Marks the student as present or absent for a particular lesson.
     *
     * @param attendance the attendance object
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

    /**
     * Adds a key,value pair to the hashmap.
     *
     * @param assessmentName key of the hashmap
     * @param marks value to be stored under the key given
     */
    public void setMarks(String assessmentName, double marks) {
        results.put(assessmentName, marks);
    }

    /**
     * Gets the marks for the given assessment.
     *
     * @param assessmentName Assessment to get marks for.
     * @return Marks for the inputted assessment.
     */
    public double getMarks(String assessmentName) {
        return results.get(assessmentName);
    }

    /**
     * Gets all assessments and their marks in a hashmap.
     *
     * @return Hashmap containing assessment names as keys and the marks as values.
     */
    public HashMap<String, Double> getAllMarks() {
        return results;
    }

    /**
     * Returns true if marks have been inputted for a given assessment.
     *
     * @param assessmentName Assessment to be checked.
     * @return True if marks have been inputted. Returns false otherwise.
     */
    public boolean marksExist(String assessmentName) {
        return results.containsKey(assessmentName);
    }

}
